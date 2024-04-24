<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Page</title>
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
          margin-left: 35%;
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
        <form action="signup.php" method="POST">
            <input type="text" name="fname" placeholder="First name" size="15" required>
            <input type="text" name="lname" placeholder="Last name" size="16" required><br/>
            <input type="text" name="id" placeholder="ID" size="40" required><br/>
            <input type="password" name="password" placeholder="Password" size="40" required><br/>
            <input type="submit" value="Request" name="submit" id="submit">
        </form>
      </div>
      <?php
        include 'connection.php';
        if(isset($_POST['submit'])){
          $sql = "INSERT INTO newstudents (fname, lname, id, password) VALUES (?, ?, ?, ?)";
          if($stmt = mysqli_prepare($link, $sql)){
              mysqli_stmt_bind_param($stmt, "ssss", $_POST['fname'],$_POST['lname'], $_POST['id'], $_POST['password']);
              if(mysqli_stmt_execute($stmt)){
                  header("Location: login.php");
              } else{
                  echo "<span>Oops! Something went wrong. Please try again later.</span>";
              }
          }
          mysqli_stmt_close($stmt);
      }
      ?>
</body>
</html>