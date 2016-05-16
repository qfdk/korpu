<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>KÖRPÜ</title>
    <!-- Bootstrap CSS -->
    <link rel="icon" href="http://qfdk.free.fr/favicon.ico" type="image/vnd.microsoft.icon">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            padding: 70px 0 20px 0;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">KÖRPÜ</a>
    </div>
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li><a href="/">Home</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right" style="margin-right: 20px;">
            <li><a href="#">Login</a></li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>

<div class="container">

    <div class="jumbotron">
        <div class="row">
            <div class="col-md-4">
                <h1>KÖRPÜ!</h1>
                <p>Sys Dropbox + Google Drive</p>
            </div>
            <div class="col-md-2">
                <img class="img-responsive" src="./img/google.png" alt="">
            </div>
            <div class="col-md-6">
                <img class="img-responsive" src="./img/dropbox.png" alt="">
            </div>
        </div>
        <a class="btn btn-primary btn-lg"
           href="https://www.dropbox.com/1/oauth2/authorize?response_type=code&client_id=yupwnr7l9luikkb&redirect_uri=http://localhost:8080/api/v1/callbackDropbox"
           role="button">Dropbox!</a>
        <a class="btn btn-info btn-lg"
           href="https://accounts.google.com/o/oauth2/auth?redirect_uri=http://localhost:8080/api/v1/callbackGoogle&response_type=code&client_id=56962053326-5j1mn79kigedokk2i7o3pc0cglgsud5b.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive&approval_prompt=force&access_type=offline"
           role="button">Google!</a>
    </div>


    <hr/>
    <p>
        <a href="about.html" target="_blank">About</a> &middot; Code licensed under <a
            href="http://www.apache.org/licenses/LICENSE-2.0"
            target="_blank">Apache
        License v2.0</a>, documentation under <a href="http://creativecommons.org/licenses/by/4.0/" target="_blank">CC
        BY 4.0</a>.
    </p>
</div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/basket.js/0.5.2/basket.full.min.js"></script>
<script type="text/javascript">
    basket.require({
        url: "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/js/collapse.min.js",
        unique: "1"
    });
</script>
</body>

</html>