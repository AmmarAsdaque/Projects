package com.ds.dsproject;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class DSProject {

    public static void main(String[] args) {
        new MainMenu();
        
    }
}
class NodeQ{ 

    public NodeQ(int oid, String item, int quantity, float bill) {
        this.oid = oid;
        this.item = item;
        this.quantity = quantity;
        this.bill = bill;
    }
    String item;
    NodeQ next;
    int oid,quantity;
    float bill;
}
class Queue{
    NodeQ front, rear;
    String roll_num;
    public Queue(String x){
        this.front = null;
        this.rear = null;
        roll_num = x;
    }
    public void EnqueueFromDB(String x){
        try{
            Class.forName("java.sql.DriverManager");
            String url="jdbc:sqlserver://asdaque.database.windows.net:1433;database=FASTCanteens;user=asdaque@asdaque;password=Ammar123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con=DriverManager.getConnection(url);
            java.sql.Statement st=con.createStatement();
            String sql="SELECT * FROM OBS";
            java.sql.ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                if(rs.getString(3).equals(x) && Boolean.valueOf(rs.getBoolean(8))){
                    NodeQ obj = new NodeQ(rs.getInt(1),rs.getString(4),rs.getInt(5),rs.getFloat(6));
                    this.Enqueue(obj);
                }
            } 
        }catch(Exception e){
                  System.out.println(e);
        }
    }
    public void Enqueue(NodeQ x){    
        if(front == null){
            front = rear = x;
            front.next = rear;
            return;
        }else{
            x.next = front;
            front = x;
        }
        
    }
    public StringBuilder display(){
        StringBuilder list = new StringBuilder();
        NodeQ temp = front;
        while(temp != rear){
            list.append(String.format("%04d", temp.oid) +"\t"+temp.item +"\t"+temp.quantity +"\t"+temp.bill +"\n");
            temp = temp.next;
        }
        return list;
    }
    public float getMoneySpent(){
        float bill = 0;
        NodeQ temp = front;
        while(temp != rear){
            bill += temp.bill;
            temp = temp.next;
        }
        return bill;
    }
}
class LL{
    class Node{    
        Node next;
        String item;
        int qty,id;
        float bill;
    }
    Node head,tail;
    int canteen_id;
    Connection con;
    Savepoint cart;
    int count;
    public LL(int id){
        canteen_id = id;
        try{
            Class.forName("java.sql.DriverManager");
            String url="jdbc:mysql://127.0.0.1:3306/mydb";
            con =DriverManager.getConnection(url);
            /*String sql="BEGIN TRANSACTION \nSAVE TRANSACTION cartsavepoint;";
            st.execute(sql);*/
            con.setAutoCommit(false);
            cart = con.setSavepoint("Cart");
            }catch(Exception e){
                  System.out.println(e);
            }
    }
    public void insertAtHead(int x,int a,String b,float c){
        
        Node newNode = new Node();
        newNode.qty = a;
        newNode.item = b;
        newNode.bill = c;
        newNode.id = x;
        count++;
        newNode.next = null;
        if(head == null){
           head = tail = newNode;
        }else{
            newNode.next = head;
            head = newNode; 
        }
    }
    public StringBuilder display(){
        Node ptr = head;
        StringBuilder info = new StringBuilder();
        while(ptr != null){
            info.append(ptr.item + "\t" + ptr.qty + "\t" + ptr.bill + "\n");
            ptr = ptr.next;
        }
        return info;
    }
    public void deleteAtHead(){
        head = head.next;
        count--;
    }
    public void commit(){
        try{
            con.commit();
            con.setAutoCommit(false);
            cart = con.setSavepoint("Cart");
        }catch(Exception e){
                  System.out.println(e);
            }
    }
    public void insertIntoDB(){
        
        try{
            java.sql.Statement st=con.createStatement();
            
            String sql="INSERT INTO Orders (`status`, `item_id`, `quantity`, `bill`, `user_id`,`canteen_id`) VALUES ('Placed',"+head.id+","+head.qty+","+head.bill+",'"+Login.roll_num+"',"+canteen_id+")";
            st.executeUpdate(sql);
            
        }catch(Exception e){
              System.out.println(e);
        }
        
    }
    public float getTotalPrice(){
        float price = 0;
        Node ptr = head;
        while(ptr != null){
            price += ptr.bill;
            ptr = ptr.next;
        }
        return price;
    }
    public void clearCart(){
        head.next = null;
        head = null;
        try{
            con.rollback(cart);
            }catch(Exception e){
                  System.out.println(e);
            }
    }
}
class Order{
    public Order(int OrderID, int qty, String id, String item, String endtime, float bill) {
        this.OrderID = OrderID;
        this.qty = qty;
        this.id = id;
        this.item = item;
        this.endtime = endtime;
        this.bill = bill;
        DateTimeFormatter obj = DateTimeFormatter.ofPattern("HH:mm:ss");
        Duration time =  Duration.between(LocalTime.now(),LocalTime.parse(endtime + ":00",obj));
        if(time.isNegative() || time.isZero())
            priorty = 0;
        else{
            priorty = (int) time.toMinutes();
        }
    }
    int OrderID,qty,priorty;
    String id,item,endtime;
    float bill;
}
class MinHeap {
    private Order[] Heap;
    private int size;
    private int maxsize;
    public MinHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new Order[this.maxsize];
    }
    public void insertFromDB(String cntn){
        int i = 0; 
        try{
            Class.forName("java.sql.DriverManager");
            String url="jdbc:sqlserver://asdaque.database.windows.net:1433;database=FASTCanteens;user=asdaque@asdaque;password=Ammar123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con=DriverManager.getConnection(url);
            java.sql.Statement st=con.createStatement();
            String sql="SELECT * FROM OBS";
            java.sql.ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                if(!Boolean.valueOf(rs.getString(8)) && rs.getString(2).equals(cntn)){
                    Order obj = new Order(rs.getInt(1),rs.getInt(5),rs.getString(3),rs.getString(4),rs.getString(7),rs.getFloat(6));
                    this.insert(obj);
                }
            } 
        }catch(Exception e){
                  System.out.println(e);
        }
        sort();
    }
    private int parent(int pos) { return (pos - 1) / 2; }
    private void swap(int fpos, int spos)
    {
        Order tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }
    private void minHeapify(int N,int i)
    {
        int largest = i;
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 
 
        if (l < N && Heap[l].priorty > Heap[largest].priorty)
            largest = l;
 
        
        if (r < N && Heap[r].priorty > Heap[largest].priorty)
            largest = r;
 
        
        if (largest != i) {
            Order swap = Heap[i];
            Heap[i] = Heap[largest];
            Heap[largest] = swap;
            minHeapify(N, largest);
        }
    }
    private void sort()
    {
        int N = size;
        for (int i = N / 2 - 1; i >= 0; i--)
            minHeapify(N,i);
        for (int i = N - 1; i > 0; i--) {
            Order temp = Heap[0];
            Heap[0] = Heap[i];
            Heap[i] = temp;
            minHeapify(i,0);
        }
    }
    public void insert(Order element)
    {
        Heap[size] = element;
        int current = size;
        while (Heap[current].priorty < Heap[parent(current)].priorty) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }
    StringBuilder display(){
        StringBuilder list = new StringBuilder();
        for(int i = 0; i < size;i++){
            list.append(Heap[i].OrderID + "\t" +Heap[i].id + "\t"+Heap[i].item + "\t"+
                    Heap[i].qty + "\t"+Heap[i].bill +"\t"+ Heap[i].endtime + "\n");
        }
        return list;
    }
    public boolean extractFromOrderID(int x){
        int i;
        for(i = 0; i < size; i++){
            if(Heap[i].OrderID == x){
                swap(0,i);
                extractMin();
                break;
            }
        }
        return !(i == size);
    }
    public void extractMin()
    {   
        if(size != 0){
            int ID = Heap[0].OrderID;
            Heap[0] = Heap[--size];
            minHeapify(size,0);
            try{
                int i = 0;
                Class.forName("java.sql.DriverManager");
                String url="jdbc:sqlserver://asdaque.database.windows.net:1433;database=FASTCanteens;user=asdaque@asdaque;password=Ammar123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                Connection con=DriverManager.getConnection(url);
                java.sql.Statement st=con.createStatement();
                String sql="UPDATE OBS SET Delivered = 'true'  WHERE OrderID = " + ID;
                st.execute(sql);

            }catch(Exception e){
                      System.out.println(e);
            }
            sort();
        }
    }
}
