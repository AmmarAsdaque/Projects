#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <conio.h>
#include <windows.h>
#include <time.h>

#define NAME_LEN 20
#define PW_LEN 20


//--------sales info-------
struct salesinfo{
	float sale;
	int date[3];
	char medicine[20];
	int quantity;
	float scost;
};


//--------stores uname and password-------
struct user{
	char name[NAME_LEN+1];
	char password[PW_LEN+1];

};

//-------medicine info--------
struct medicine{
	char mname[NAME_LEN+1];
	char brand[NAME_LEN];
	char chemical[30];
	int qty;
	float price; 
	float cost;
	
};

//------order details-----
struct order{
	char address[NAME_LEN+20];
	char number[NAME_LEN];
	char oname[NAME_LEN+1];
	int oqty;
	char omed[NAME_LEN+1];
	float total;
};

//----Main functions---
void signUp();
void signIn();
void login();


//---Portals----
void manager();
void admin();
void customer();

//--------MANAGER FUNCTIONS + ADMIN FUNCTIONS-------
void orders();
void recordMenu();
void recordsScreen();
void stockMenu();
void stockScreen();
void feedbacks();
void request();
void viewstockscustomer();
void vouchers();
void managerrequests(); 
void salesadmin();
void salesmanager();
void Addrecord();
void Deleterecord();
void Additem();
void Deleteitem();
void Modifyitem();
void Viewitem();
void gotoxy(int x,int y);
void loading(int N);
void mainadminmenu();
void mainmanagermenu();
void maincustomermenu();
void customer_order();
void customer_view();
void customer_feedback();

int main(){
	int i;
	time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	gotoxy(15,8);
	for(i=0;i<42;i++)
    	printf("%c",219);
    gotoxy(15,9);
    printf("%c%cWelcome To Pharmacy Management System!%c%c",219,219,219,219);
    gotoxy(15,10);
	for(i=0;i<42;i++)
    	printf("%c",219);	
    loading(10000000);
    gotoxy(19,15);
    printf("Press any key to continue.");
    getch();
    login();
   
}
void login(){
	system("cls");
	int choice,i;
	gotoxy(15,3);
	for(i=0;i<15;i++)
    	printf("%c",219);
    gotoxy(15,4);
    printf("%c%c Main Menu %c%c",219,219,219,219);
    gotoxy(15,5);
	for(i=0;i<15;i++)
    	printf("%c",219);
	gotoxy(9,7);
	for(i=0;i<27;i++)
    	printf("%c",219);
    gotoxy(9,8);
    printf("%c 1 : Sign In             %c",219,219);
    gotoxy(9,9);
    printf("%c 2 : Sign Up             %c",219,219);
    gotoxy(9,10);
    printf("%c  Any other key to exit  %c",219,219);
    gotoxy(9,11);
    printf("%c     Choose an option:   %c",219,219);
    gotoxy(9,13);
	for(i=0;i<27;i++)
    	printf("%c",219);
	gotoxy(9,12);
	printf("%c                         %c",219,219);
	gotoxy(19,12);
	scanf("%d",&choice);
	getchar();
	if(choice==1)
		signIn();
	else if(choice == 2)
		signUp();
}
void signIn(){                                                                             
	struct user user;
	struct user fileUser;
	printf("\n\n\n\t\t\tEnter username: ");
	gets(user.name);
	printf("\t\t\tEnter password: ");
	char c;
	int index=0;
	getch();
	while((c = getch()) != 13){
		if(index < 0)
		index = 0;
		if(c == 8){
			putch('\b');
			putch('\0'); 
			putch('\b');
			index--;
			continue;
		}
		user.password[index++] = c;
		putch('*');
		}
		user.password[index] = '\0';
	//ADMIN AND MANAGER's id password are hardcoded
	if(strcmp(user.name, "Taha") == 0 && strcmp(user.password, "taha1234") == 0)                
			manager();
		
	else if(strcmp(user.name, "Ammar") == 0 && strcmp(user.password, "ammar123") == 0)
			admin();
	else
	{
	FILE *fp=fopen("customers.txt", "a+");//r r+ w w+ a a+
	while(fread(&fileUser, sizeof(struct user), 1, fp)==1){
		if (strcmp(user.name, fileUser.name) == 0 && strcmp(user.password, fileUser.password) == 0)
			customer();
	}
	fclose(fp);
	printf("\n\n\n\t\tUser doesnt exist. Create a new account to continue");
	signIn();
}
}
void signUp(){
	struct user u;
	struct user file;
	printf("\n\n\n\t\t\tEnter username: ");
	gets(u.name);
	printf("\t\t\tEnter password: ");
	gets(u.password);
	FILE *fp = fopen("customers.txt", "a+");
	while(fread(&file,sizeof(struct user),1,fp)==1)
	{		if(strcmp(u.name,file.name)==0)
			{
				printf("\n\t\t\tUser name already exists, choose a unique username\n");
				signUp();
			}
	}
	fclose(fp);
	fp=fopen("customers.txt", "a");
	fwrite(&u,sizeof(struct user),1,fp);
	fclose(fp);
	printf("\n\t\t\tSign Up successful!\n You can now login\n");
	login();
}
void admin(){
	system("cls");
	int i;
	time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	gotoxy(15,8);
	for(i=0;i<18;i++)
    	printf("%c",219);
    gotoxy(15,9);
    printf("%c%cWelcome Admin!%c%c",219,219,219,219);
    gotoxy(15,10);
	for(i=0;i<18;i++)
    	printf("%c",219);	
    loading(10000000);
    gotoxy(19,15);
    printf("Press any key to continue.");
    getch();
	mainadminmenu();
}
void mainadminmenu(){
	system("cls");
	int option;
	time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	int i;
	gotoxy(15,3);
	for(i=0;i<16;i++)
    	printf("%c",219);
    gotoxy(15,4);
    printf("%c%c Admin Menu %c%c",219,219,219,219);
    gotoxy(15,5);
	for(i=0;i<16;i++)
    	printf("%c",219);
	gotoxy(9,7);
	for(i=0;i<27;i++)
    	printf("%c",219);
    gotoxy(9,8);
    printf("%c 1 : Customer Accounts   %c",219,219);
    gotoxy(9,9);
    printf("%c 2 : Stock Management    %c",219,219);
    gotoxy(9,10);
    printf("%c 3 : Customer Feedback   %c",219,219);
    gotoxy(9,11);
    printf("%c 4 : Manager's Requests  %c",219,219);
    gotoxy(9,12);
    printf("%c 5 : Profit/Sales Info   %c",219,219);
    gotoxy(9,13);
    printf("%c 6 : Sign Out            %c",219,219);
	gotoxy(9,14);
    printf("%c  Any other key to exit  %c",219,219);
    gotoxy(9,15);
    printf("%c     Choose an option:   %c",219,219);
    gotoxy(9,17);
	for(i=0;i<27;i++)
    	printf("%c",219);
	gotoxy(9,16);
	printf("%c                         %c",219,219);
	gotoxy(19,16);
	scanf("%d",&option);
	switch(option)
	{
		case 1:
		system("cls");
		recordsScreen();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainadminmenu();
	case 2:
		system("cls");
		stockScreen();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainadminmenu();
	case 3:
		system("cls");
		feedbacks();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainadminmenu();
	case 4:
		system("cls");
		managerrequests();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainadminmenu();
	case 5:
		system("cls");
		salesadmin();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainadminmenu();
	case 6:
		login();
	default:
		printf("\n\n            Press any key to Exit.");
		getch();	
	}
    
}
void recordsScreen(){
	int i;
	time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	gotoxy(20,8);
	for(i=0;i<21;i++)
    	printf("%c",219);
    gotoxy(20,9);
    printf("%c%cCustomer Accounts%c%c",219,219,219,219);
    gotoxy(20,10);
	for(i=0;i<21;i++)
    	printf("%c",219);	
    loading(10000000);
    gotoxy(19,15);
    printf("Press any key to continue.");
    recordMenu();
    getch();
}
void recordMenu(){
	system("cls");
	int option;
    time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	int i;
	gotoxy(13,3);
	for(i=0;i<20;i++)
    	printf("%c",219);
    gotoxy(13,4);
    printf("%c%cCustomer Records%c%c",219,219,219,219);
    gotoxy(13,5);
	for(i=0;i<20;i++)
    	printf("%c",219);
	gotoxy(9,7);
	for(i=0;i<27;i++)
    	printf("%c",219);
	gotoxy(9,8);
    printf("%c 1 : Add Account.        %c",219,219);
    gotoxy(9,9);
    printf("%c 2 : Delete Account.     %c",219,219);
    gotoxy(9,10);
    printf("%c  Any other key to exit  %c",219,219);
    gotoxy(9,11);
	printf("%c Choose an option:       %c",219,219);
	gotoxy(9,13);
	for(i=0;i<27;i++)
    	printf("%c",219);
	gotoxy(9,12);
	printf("%c                         %c",219,219);
	gotoxy(19,12);
	scanf("%d",&option);

	switch(option){
	case 1:
		{
		system("cls");
		Addrecord();
		printf("\nPress any key to go back.");
		getch();
		getch();
		recordMenu();
		break;
		}
	case 2:
		{
		system("cls");
		Deleterecord();
		printf("\nPress any key to go back.");
		getch();
		getch();
		recordMenu();
		break;
		}
	default:
		{
		fflush(stdin);
		printf("\n\n            Press any key to Exit.");
		getch();
		}
	}
}
void Addrecord(){
	int flag=0;
	struct user c1,temp;
    FILE *fptr,*ft;
    fptr=fopen("customers.txt","a+");
	system("cls");
    printf("\nEnter Name: ");
    fflush(stdin);
    gets(c1.name);
    ft=fopen("customers.txt","r");
    while(fread(&temp,sizeof(temp),1,ft)==1){
    	if(strcmp(c1.name,temp.name)==0){
			flag=1;
		}
	}
	fclose(ft);
	if(flag==1){
		printf("\nThis Name already exists can't add again!\nPress any key to go back");
		getch();
	}else{
        printf("\nEnter password: ");
        fflush(stdin);
		gets(c1.password);
        fwrite(&c1,sizeof(c1),1,fptr);
    }
    fclose(fptr);
}
void Deleterecord(){
	struct user c1;
	char nameDLT[20],flag=0;
    FILE *fptr,*temp;
    fptr=fopen("customers.txt","r+");
    if(fptr==NULL){
        printf("File could not be opened!");
        return;
    }
    temp=fopen("temp.txt","w+");
    printf("Which record do u want to delete?\nEnter the name of the customer:");
    fflush(stdin);
    gets(nameDLT);
    while (fread(&c1,sizeof(c1),1,fptr)==1)
    {
    	if(strcmp(nameDLT,c1.name)==0)
			flag=1;		
		else{
			fwrite(&c1,sizeof(c1),1,temp);
		}
    }
    fclose(fptr);
	fclose(temp);
    if(flag==1){
		remove("customers.txt");
	    rename("temp.txt","customers.txt");
	    printf("\nRecord Deleted Successfully!");
    }
    else{
    	remove("temp.txt");
    	printf("\nThere is no customer with that name!");
	}
}
void stockScreen(){
	int i;
	time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	gotoxy(20,8);
	for(i=0;i<20;i++)
    	printf("%c",219);
    gotoxy(20,9);
    printf("%c%cStock Management%c%c",219,219,219,219);
    gotoxy(20,10);
	for(i=0;i<20;i++)
    	printf("%c",219);	
    loading(10000000);
    gotoxy(19,15);
    printf("Press any key to continue.");
    getch();
	stockMenu();
}
void stockMenu(){
	system("cls");
	int option;
	int i;
	gotoxy(12,3);
	for(i=0;i<20;i++)
    	printf("%c",219);
    gotoxy(12,4);
    printf("%c%cStock Management%c%c",219,219,219,219);
    gotoxy(12,5);
	for(i=0;i<20;i++)
    	printf("%c",219);
	gotoxy(9,7);
	for(i=0;i<27;i++)
    	printf("%c",219);
	gotoxy(9,8);
    printf("%c 1 : Add Products.       %c",219,219);
    gotoxy(9,9);
    printf("%c 2 : View Products.      %c",219,219);
    gotoxy(9,10);
    printf("%c 3 : Modify Products.    %c",219,219);
    gotoxy(9,11);
    printf("%c 4 : Delete Products.    %c",219,219);
    gotoxy(9,12);
    printf("%c  Any other key to exit  %c",219,219);
    gotoxy(9,13);
	printf("%c Choose an option:       %c",219,219);
	gotoxy(9,15);
	for(i=0;i<27;i++)
    	printf("%c",219);
	gotoxy(9,14);
	printf("%c                         %c",219,219);
	gotoxy(19,14);
	scanf("%d",&option);
	switch(option){
	case 1:
		{
		system("cls");
		Additem();
		puts("\n\nPress any key to go back.");
		getch();
		getch();
		stockMenu();
		break;
		}
	case 2:
		{
		system("cls");
		Viewitem();
		puts("\n\nPress any key to go back.");
		getch();
		getch();
		stockMenu();
		break;
		}
	case 3:
		{
		system("cls");
		Modifyitem();
		puts("\nPress any key to go back.");
		getch();
		getch();
		stockMenu();
		break;
		}
	case 4:
		{
		system("cls");
		Deleteitem();
		puts("\nPress any key to go back.");
		getch();
		getch();
		stockMenu();
		break;
		}
	default:
		{
		fflush(stdin);
		puts("\n\n            Press any key to Exit.");
		getch();
		}
	}
}
void Additem(){
	int flag=0;
	struct medicine it,temp;
    FILE *fp,*ft;
    fp=fopen("medicines.txt","a+");
    while (1)
    {
    	system("cls");
    	printf("\nEnter Product Name: ");
        fflush(stdin);
        gets(it.mname);
        it.mname[0]=toupper(it.mname[0]);
        ft=fopen("medicines.txt","r");
    	while(fread(&temp,sizeof(struct medicine),1,ft)==1){
    		if(strcmp(it.mname,temp.mname)==0){
				flag=1;
			}
		}
		fclose(ft);
		if(flag==1){
			printf("\nThis Product already exists hence can't add again!\nPress any key to go back");
			getch();
			getch();
			break;
		}
        
        printf("\nEnter Product Company: ");
        fflush(stdin);
        gets(it.brand);
        it.brand[0]=toupper(it.brand[0]);
        printf("\nEnter Product Chemical: ");
        fflush(stdin);
        gets(it.chemical);
        it.chemical[0]=toupper(it.chemical[0]);
		while(1){
		printf("\nEnter Product Cost: ");
        scanf("%f",&it.cost);
		printf("\nEnter Product Sale Price: ");
        scanf("%f",&it.price);
        printf("\nEnter Product Quantity: ");
        scanf("%d",&it.qty);
        if((it.price>0)&&(it.qty>0)&&(it.cost>0))
        	break;
        else
        	printf("\nPrice, sale price and quantity must be greater than 0");
		}
        fwrite(&it,sizeof(struct medicine),1,fp);
        break;
    }
    fclose(fp);
}
void Viewitem(){
	struct medicine it;
    FILE *fp;
    fp=fopen("medicines.txt","r");
    if(fp==NULL){
        printf("No medicines are in stock currently");
    	return;
	}
    while (fread(&it,sizeof(it),1,fp)==1)
    {
		printf("\n\nProduct Name: %s", it.mname);
		printf("\nBrand: %s", it.brand);
		printf("\nProduct Chemical: %s",it.chemical);
		printf("\nProduct Cost: %.2f", it.cost);
		printf("\nProduct Price: %.2f", it.price);
		printf("\nProduct Quantity: %d", it.qty);
    }
    fclose(fp);
}
void Modifyitem(){
	struct medicine it;
	char nameMOD[20],flag=0;
    FILE *fp,*temp;
    fp=fopen("medicines.txt","r+");
    temp=fopen("temp.txt","w+");
    printf("Which record do u want to modify?\nEnter the Product Name:");
    fflush(stdin);
    gets(nameMOD);
	nameMOD[0]=toupper(nameMOD[0]);
    while (fread(&it,sizeof(it),1,fp)==1)
    {		
		if(strcmp(nameMOD,it.mname)!=0){
			fwrite(&it,sizeof(it),1,temp);
		}else{
			flag=1;
			printf("\n\nProduct Name: %s", it.mname);
			printf("\nBrand: %s", it.brand);
			printf("\nProduct Chemical: %s",it.chemical);
			printf("\nProduct Cost: %.2f", it.cost);
			printf("\nProduct Price: %.2f", it.price);
			printf("\nProduct Quantity: %d", it.qty);
			printf("\nEnter Product Name: ");
        	fflush(stdin);
        	gets(it.mname);
			printf("\nEnter Product Company: ");
    	    fflush(stdin);
    	    gets(it.brand);
    	    it.brand[0]=toupper(it.brand[0]);
    	    printf("\nEnter Product Chemical: ");
    	    fflush(stdin);
    	    gets(it.chemical);
			it.chemical[0]=toupper(it.chemical[0]);
			while(1){
				printf("\nEnter Product Cost: ");
        		scanf("%f",&it.cost);
				printf("\nEnter Product Sale Price: ");
        		scanf("%f",&it.price);
				printf("\nEnter Product Quantity: ");
		        scanf("%d",&it.qty);
    	    	if((it.price>0)&&(it.qty>0)&&(it.cost>0))
					break;
        		else
        			printf("\nPrice, sale price and quantity must be greater than 0");
				}
	        fwrite(&it,sizeof(it),1,temp);
		}
    }
	fclose(fp);
	fclose(temp);
    if(flag==1){
		remove("medicines.txt");
	    rename("temp.txt","medicines.txt");
	    printf("\nRecord Modified Successfully!");
    }
    else{
    	remove("temp.txt");
    	printf("\nThere is no Product with that Name!");
	}
}
void Deleteitem(){
	struct medicine it;
	char nameDLT[20],flag=0;
    FILE *fp,*temp;
    fp=fopen("medicines.txt","r+");
    temp=fopen("temp.txt","w+");
    printf("Which record do u want to delete?\nEnter the Product Name:");
    fflush(stdin);
    gets(nameDLT);
	nameDLT[0]=toupper(nameDLT[0]);
    while (fread(&it,sizeof(it),1,fp)==1)
    {
    	if(strcmp(nameDLT,it.mname)==0)
			flag=1;		
		else
			fwrite(&it,sizeof(it),1,temp);
    }
    //close files before renaming and deleting
    fclose(fp);
	fclose(temp);
    if(flag==1){
		remove("medicines.txt");
	    rename("temp.txt","medicines.txt");
	    printf("\nRecord Deleted Successfully!");
    }
    else{
    	remove("temp.txt");
    	printf("\nThere is no product with that Name!");
	}
}
void feedbacks(){
	char fback[100];
	int i;
	gotoxy(0,4);
	for(i=0;i<22;i++)
		printf("%c",219);
	gotoxy(0,5);
	printf("%c CUSTOMER FEEDBACKS %c",219,219);
	gotoxy(0,6);
	for(i=0;i<22;i++)
		printf("%c",219);
	FILE *fb;
	fb = fopen("feedback.txt","a+");
	gotoxy(0,8);
	while(fread(&fback, sizeof(fback), 1, fb)==1){
        printf("> %s\n",fback);
    }
    fclose(fb);	
}
void managerrequests(){
	char requests[100];
	int i;
	gotoxy(0,4);
	for(i=0;i<20;i++)
		printf("%c",219);
	gotoxy(0,5);
	printf("%c MANAGER REQUESTS %c",219,219);
	gotoxy(0,6);
	for(i=0;i<20;i++)
		printf("%c",219);
	FILE *fp;
	fp = fopen("requests.txt","a+");
	gotoxy(0,8);
	while(fread(&requests, sizeof(requests), 1, fp)==1)
    	printf("> %s\n",requests);
    fclose(fp);	
}
void salesadmin(){
	float sales=0;
	float costs = 0;
	struct salesinfo sinfo;
	FILE *fp;
	fp = fopen("sales.txt","a+");
	int i=1,j;
	gotoxy(15,8);
	for(j=0;j<46;j++)
		printf("%c",219);
	gotoxy(15,9);
	printf("%c%c FOLLOWING ARE THE SALES AND PROFIT INFO  %c%c",219,219,219,219);
	gotoxy(15,10);
	for(j=0;j<46;j++)
		printf("%c",219);
	while(fread(&sinfo,sizeof(struct salesinfo),1,fp)==1)
	{
		sales = sinfo.sale + sales;
		costs = costs + sinfo.scost;
		printf("\n\t%d) Medicine: %s\t\tQty: %d\t\tSale: %f\t\tDate:%d/%d/%d\n",i,sinfo.medicine, sinfo.quantity, sinfo.sale, sinfo.date[0],sinfo.date[1],sinfo.date[2]);
		i++;
	}
	fclose(fp);
	gotoxy(15,20);
	for(j=0;j<35;j++)
		printf("%c",219);
	gotoxy(15,21);
	printf("%c TOTAL SALES:  RS %.2f",219,sales);
	gotoxy(15,22);
	printf("%c TOTAL PROFIT: RS %.2f",219,sales-costs);
	gotoxy(49,21);
	printf("%c",219);
	gotoxy(49,22);
	printf("%c",219);
	gotoxy(15,23);
	for(j=0;j<35;j++)
		printf("%c",219);
	gotoxy(1,24);
	printf("Press 1 to clear sales and profit info:");
	int choice;
	scanf("%d",&choice);
	if(choice==1){
	fp = fopen("sales.txt","w");
	fclose(fp);
	printf("\n\n\t\t\tSales info have been cleared");
	}	
}
void manager(){
	system("cls");
	int i;
	time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	gotoxy(15,8);
	for(i=0;i<20;i++)
    	printf("%c",219);
    gotoxy(15,9);
    printf("%c%cWelcome Manager!%c%c",219,219,219,219);
    gotoxy(15,10);
	for(i=0;i<20;i++)
    	printf("%c",219);	
    loading(10000000);
    gotoxy(19,15);
    printf("Press any key to continue.");
    getch();
	mainmanagermenu();
}
void mainmanagermenu(){
	system("cls");
	int option;
	time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	int i;
	gotoxy(15,3);
	for(i=0;i<18;i++)
    	printf("%c",219);
    gotoxy(15,4);
    printf("%c%c Manager Menu %c%c",219,219,219,219);
    gotoxy(15,5);
	for(i=0;i<18;i++)
    	printf("%c",219);
	gotoxy(9,7);
	for(i=0;i<27;i++)
    	printf("%c",219);
    gotoxy(9,8);
    printf("%c 1 : Pending Orders      %c",219,219);
    gotoxy(9,9);
    printf("%c 2 : View Stocks         %c",219,219);
    gotoxy(9,10);
    printf("%c 3 : Add/View Vouchers   %c",219,219);
    gotoxy(9,11);
    printf("%c 4 : Request from Admin  %c",219,219);
    gotoxy(9,12);
    printf("%c 5 : Sales Report        %c",219,219);
    gotoxy(9,13);
    printf("%c 6 : Sign Out            %c",219,219);
	gotoxy(9,14);
    printf("%c  Any other key to exit  %c",219,219);
    gotoxy(9,15);
    printf("%c     Choose an option:   %c",219,219);
    gotoxy(9,17);
	for(i=0;i<27;i++)
    	printf("%c",219);
	gotoxy(9,16);
	printf("%c                         %c",219,219);
	gotoxy(19,16);
	scanf("%d",&option);
	switch(option)
	{
		case 1:
		system("cls");
		orders();	
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainmanagermenu();
	case 2:
		system("cls");
		Viewitem();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainmanagermenu();
	case 3:
		system("cls");
		vouchers();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainmanagermenu();
	case 4:
		system("cls");
		request();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainmanagermenu();
	case 5:
		system("cls");
		salesmanager();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		mainmanagermenu();
	case 6:
		login();
	default:
		printf("\n\n            Press any key to Exit.");
		getch();
	}
    
}
void orders(){
	struct order orderfile;
	int i;                           
	gotoxy(15,3);
	for(i=0;i<18;i++)
    	printf("%c",219);
    gotoxy(15,4);
	printf("%c PENDING ORDERS %c",219,219);
	gotoxy(15,5);
	for(i=0;i<18;i++)
    	printf("%c",219);
	FILE * fp = fopen("orders.txt", "r");
	gotoxy(1,5);
	while(fread(&orderfile, sizeof(struct order), 1, fp)==1){
		printf("\n\n\nName: %s\nAddress: %s\nPhone Number: %s\nQty: %d\nMedicine: %s\nTotal: %f",orderfile.oname,orderfile.address, orderfile.number,orderfile.oqty,orderfile.omed,orderfile.total);
	}
}
void vouchers(){
	FILE *fp;
	int i=1,j;
	char voucher[5];
	fp=fopen("vouchers.txt","a+");
	gotoxy(15,3);
	for(j=0;j<23;j++)
		printf("%c",219);
	gotoxy(15,4);
	printf("%c  Current Vouchers   %c",219,219);
	gotoxy(15,5);
	for(j=0;j<23;j++)
		printf("%c",219);
	gotoxy(1,7);
	while(fread(&voucher,sizeof(voucher),1,fp)==1){
		printf("\t\t%d) %s\n",i,voucher);
		i++;
	}
	printf("\n\n\n\n\n\n\t\t\tPress 1 to add voucher, 2 to clear all vouchers: ");
	int choice; 
	scanf("%d",&choice);
	if(choice==1){
	printf("\n\nEnter voucher(five lettered): ");
	scanf("%s",&voucher);
	fwrite(&voucher,sizeof(voucher),1,fp);
	printf("\n\t\t\tVOUCHER ADDED!\n\n\n\n\n");
	fclose(fp);
	}
	else if(choice==2){
	fp = fopen("vouchers.txt","w");
	fclose(fp);
	printf("\n\n\t\t\tALL VOUCHERS HAVE BEEN CLEARED!\n\n\n\n\n");
	}	
}
void request(){
	FILE *fp;
	char requests[100];
	fp = fopen("requests.txt","a");
    printf("\n\n\t\t\t\tEnter your request: ");
    fflush(stdin);
	gets(requests);
	fflush(stdin);
    fwrite(&requests, sizeof(requests),1,fp);
	fclose(fp);
	printf("\n\t\t\t\tYour request has been sent to admin\n");
		    	
}
void salesmanager(){
	float sales=0;
	struct salesinfo sinfo;
	FILE *fp;
	fp = fopen("sales.txt","a+");
	int i=1,j;
	gotoxy(15,3);
	for(j=0;j<24;j++)
		printf("%c",219);
	gotoxy(15,4);
	printf("%c  Sales Information   %c",219,219);
	gotoxy(15,5);
	for(j=0;j<24;j++)
		printf("%c",219);
	gotoxy(1,5);
	while(fread(&sinfo,sizeof(struct salesinfo),1,fp)==1)
	{
		printf("\n\n%d) Medicine: %s\t\tQty: %d\t\tSale: %f\t\tDate:%d/%d/%d\n",i,sinfo.medicine, sinfo.quantity, sinfo.sale, sinfo.date[0],sinfo.date[1],sinfo.date[2]);
		i++;
	}
	fclose(fp);
}
void customer(){
	system("cls");
	int i;
	time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	gotoxy(15,8);
	for(i=0;i<20;i++)
    	printf("%c",219);
    gotoxy(15,9);
    printf("%c%cWelcome Customer%c%c",219,219,219,219);
    gotoxy(15,10);
	for(i=0;i<20;i++)
    	printf("%c",219);	
    loading(10000000);
    gotoxy(19,15);
    printf("Press any key to continue.");
    getch();
    maincustomermenu();
}
void maincustomermenu(){
	system("cls");
	int option;
	time_t t;
	time(&t);
	printf("\n\t\t\t\t\t\t%s",ctime(&t));
	int i;
	gotoxy(15,3);
	for(i=0;i<19;i++)
    	printf("%c",219);
    gotoxy(15,4);
    printf("%c%c Customer Menu %c%c",219,219,219,219);
    gotoxy(15,5);
	for(i=0;i<19;i++)
    	printf("%c",219);
	gotoxy(9,7);
	for(i=0;i<27;i++)
    	printf("%c",219);
    gotoxy(9,8);
    printf("%c 1 : Order Now           %c",219,219);
    gotoxy(9,9);
    printf("%c 2 : View Medicines      %c",219,219);
    gotoxy(9,10);
    printf("%c 3 : Give Feedback       %c",219,219);
    gotoxy(9,11);
    printf("%c 4 : Sign Out            %c",219,219);
	gotoxy(9,12);
    printf("%c  Any other key to exit  %c",219,219);
    gotoxy(9,13);
    printf("%c     Choose an option:   %c",219,219);
    gotoxy(9,15);
	for(i=0;i<27;i++)
    	printf("%c",219);
	gotoxy(9,14);
	printf("%c                         %c",219,219);
	gotoxy(19,14);
	scanf("%d",&option);
	switch(option)
	{
		case 1:
		system("cls");
		customer_order();	
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		maincustomermenu();
	case 2:
		system("cls");
		customer_view();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		maincustomermenu();
	case 3:
		system("cls");
		customer_feedback();
		printf("\n\nPress any key to go back.");
		getch();
		getch();
		maincustomermenu();
	case 4:
		login();
	default:
		printf("\n\n            Press any key to Exit.");
		getch();
	}
}
void customer_order(){
	int quant,qty,flag=0,i,x;
	struct order o;
	char medi[21],userinput[21],mname[NAME_LEN];
	struct medicine mfile,m,count,marray[30];
	struct salesinfo sinfo;
	fflush(stdin);
	printf("\nType the medicine name and quantity\n");
	gets(m.mname);
	strcpy(userinput,m.mname);
	scanf("%d",&m.qty);
	int userqty = m.qty;
	FILE *fp = fopen("medicines.txt", "a+");
	while(fread(&mfile, sizeof(struct medicine), 1, fp)==1){                                                                  
		if (strcmp(m.mname, mfile.mname)==0 && mfile.qty>=m.qty){
			printf("\n\n\t\t\t\t\t_MEDICINE AVAILABLE_\n\n\n");
			strcpy(sinfo.medicine, m.mname);
			sinfo.quantity = m.qty;
			sinfo.scost = m.qty * mfile.cost;
			flag = 1;
			fclose(fp);
			fp = fopen("orders.txt", "a");
			strcpy(o.omed,m.mname);
			o.oqty = m.qty;
			printf("\n\n\n\t\t\tProvide details for processing your order\n\nEnter your address: ");
			fflush(stdin);
			gets(o.address);
			fflush(stdin);
			printf("Enter your name: ");
			fflush(stdin);
			gets(o.oname);
			printf("Enter Phone number: ");
			fflush(stdin);
			gets(o.number);
			fflush(stdin);
			printf("Enter voucher code for 10 percent discount (5 characters long), press any key to skip: ");
			fflush(stdin);
			char voucher[5];
			fflush(stdin);
			scanf("%s",&voucher);
			fflush(stdin);
			FILE *fptr;
			char filevoucher[5];                    
			int flag1 = 0;
			fptr = fopen("vouchers.txt","a+");
			while(fread(&filevoucher,sizeof(voucher),1,fptr)==1)
				{
					if(strcmp(filevoucher,voucher)==0)
					{
						flag1 = 1;                     
						printf("\n\n\n\t\t\tCongratulations! You got a 10 percent discount of RS %f\n",((0.1)*(userqty * mfile.price)));
						o.total = (userqty * mfile.price)-((0.1)*(userqty * mfile.price));
						break;
					}
				}if(flag1==0)                                
				{
					    printf("\n\n\t\t\tVoucher not applied!\n");
						o.total = userqty * mfile.price; 
				}
				fclose(fptr);
				SYSTEMTIME stime;
	            GetSystemTime(&stime);
				sinfo.sale = o.total;
				sinfo.date[0] = stime.wDay;
				sinfo.date[1] = stime.wMonth;
				sinfo.date[2] = stime.wYear;
				FILE *fp2 = fopen("sales.txt","a+");	
				fwrite(&sinfo,sizeof(struct salesinfo),1,fp2);
				fclose(fp2);
				fwrite(&o, sizeof(struct order), 1, fp);
				fclose(fp);
                fp=fopen("medicines.txt", "r");
				int linecount=0;
				while(fread(&count,sizeof(struct medicine),1,fp)==1)
					linecount++;
				fclose(fp);
				fp = fopen("medicines.txt", "a+");
				for(i=0;i<linecount;i++){                                          
		            if (fread(&mfile, sizeof(struct medicine), 1, fp)!=1){
			            break;
	                }
					strcpy(marray[i].mname, mfile.mname);
					strcpy(marray[i].brand, mfile.brand);
					strcpy(marray[i].chemical, mfile.chemical);
					marray[i].price = mfile.price;
					marray[i].cost = mfile.cost;
			
					if(strcmp(marray[i].mname,userinput)==0)
						marray[i].qty = mfile.qty - userqty;
					else
						marray[i].qty = mfile.qty;
				}
				fclose(fp);
                fp = fopen("medicines.txt","w");                        
				for(i=0;i<linecount;i++){
		            if (feof(fp)){
				        break;
		            }
					    fwrite(&marray[i], sizeof(struct medicine), 1, fp);    
		        }
		        fclose(fp);
		        system("cls");
		        
		        printf("\n\t\t\t\t__Your order has been placed___\n\t\t\t\tTotal Amount: Rs %f\n",o.total);
		        break;
        }else
		continue;	
    }
    if(flag==0)
    	printf("\nMedicine does not exist or the stock is empty\n");
    fclose(fp);
}
void customer_feedback(){
	char feedback[100];
	FILE *fb;
	printf("\nWrite your feedback here: ");
	fflush(stdin);
	gets(feedback);
	fb = fopen("feedback.txt","a");
	fwrite(feedback,sizeof(feedback),1,fb);
	fclose(fb);
	printf("\nFeedback sent!\n");
}
void customer_view(){
	struct medicine filemedicine;
	int i;
	gotoxy(10,2);
	for(i=0;i<14;i++)
		printf("%c",219);
	gotoxy(10,3);	
	printf("%c%c  STOCKS  %c%c",219,219,219,219);
	gotoxy(10,4);
	for(i=0;i<14;i++)
		printf("%c",219);
	gotoxy(0,9);
	FILE * fp = fopen("medicines.txt", "a+");	
	while(fread(&filemedicine, sizeof(struct medicine), 1, fp)==1)
		printf("Medicine name: %s\nBrand:%s\nChemical: %s\nSale Price:RS %f\nQty: %d\n",filemedicine.mname,filemedicine.brand,filemedicine.chemical,filemedicine.price, filemedicine.qty);
	fclose(fp);
}
void gotoxy(int x,int y) //function to print anything at desired position...
{
    COORD c;
    c.X=x;
    c.Y=y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),c);
}
void loading(int N) //function for loading screen
{	
	int i,j; 
	gotoxy(25,12);
	printf("Loading....");
	gotoxy(23,14);
	for(i=1;i<=15;i++)
	{
		for(j=0; j<=N;j++); //determine speed of loading screen
		{
			printf("%c", 219); //219 is ASCII code for Character which is to be printed
		}
	}
}
