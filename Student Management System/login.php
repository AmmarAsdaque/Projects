<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
          display: flex;
          justify-content: center;
          align-items: center;
          margin-top: 12.5%;
          font-size: 20px;
          background-color: gray;
        }
        form {
          padding: 20px;
          border: 2px solid black;
          border-radius: 10px;
          background-color: white;
        }
        form input{
          margin-top: 35px;
          background-color: white;
          padding: 10px;
        }
        form input {
          border-radius: 5px;
        }
        #submit {
          margin-left: 15%;
          padding-left: 20px;
          padding-right: 20px;
          padding-top: 10px;
          padding-bottom: 10px;
          color: white;
          background-color: black;
          cursor: pointer;
          border-radius: 10rem;
        }
        #submit:hover{
            background-color: goldenrod;
            border: 2px solid goldenrod;
        }
      </style>
    </head>
    <body>
      <div id="form">
        <form action="login.php" method="POST">
          <input type="text" name="id" id="name" placeholder="Enter ID" size="40"><br/>
          <input type="password" name="password" id="email" placeholder="Enter Password" size="40"><br/>
          <div>
            <input type="submit" value="Login" name="submit" id="submit">
            <input type="submit" value="Register" name="reg" id="submit">
          </div>
        </form>
      </div>
      <?php
        session_start();
        include 'connection.php';
        if(isset($_POST['submit'])){
          $sql="SELECT * FROM user";
          if($result=mysqli_query($link,$sql)){
            if(mysqli_num_rows($result)>0){
              while($row=mysqli_fetch_array($result)){
                if($row['id']==$_POST['id'] && $_POST['password']==$row['password']){
                  $_SESSION['role']=$row['role'];
                  $_SESSION['id']=$row['id'];
                  header("Location: home.php");
                  exit();
                }
              }
            }else{
              echo "Error wrong id and password";
            }
          }
        }else if(isset($_POST['reg'])){
          header("Location: signup.php");
          exit();
        }
      ?>
</body>
</html>