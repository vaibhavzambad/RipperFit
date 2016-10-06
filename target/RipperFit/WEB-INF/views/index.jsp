<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="RipperFit - A Resource Tracking App">
    <meta name="author" content="VARAAS">
    <link rel="shortcut icon" href="assets/ico/favicon.png">

    <title>RipperFit</title>

    <!-- Bootstrap core CSS -->
    <link href="/assets/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/assets/css/main.css" rel="stylesheet">
	<link rel="stylesheet" href="/assets/css/font-awesome.min.css">
    
    <!--Newly Added for Features Icons
    <link rel='stylesheet' id='layerslider-group-css' href='https://www.lucep.com/wp-content/plugins/bwp-minify/min/?f=wp-content/plugins/LayerSlider/static/css/layerslider.css,wp-content/plugins/revslider/public/assets/css/settings.css,wp-content/themes/Avada/style.css,wp-content/themes/Avada/assets/fonts/fontawesome/font-awesome.css,wp-content/themes/Avada/ilightbox.css,wp-content/themes/Avada/animations.css,wp-content/uploads/avada-styles/avada-791.css' type='text/css' media='all' />-->


    <script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/Chart.js"></script>
	<script src="/assets/js/modernizr.custom.js"></script>
	

	
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="assets/js/html5shiv.js"></script>
      <script src="assets/js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body data-spy="scroll" data-offset="0" data-target="#theMenu">
		
	<!-- Menu -->
	<nav class="menu" id="theMenu">
		<div class="menu-wrap">
			<h1 class="logo"><a href="index.html#home">RipperFit</a></h1>
			<i class="icon-remove menu-close"></i>
			<a href="#home" class="smoothScroll">Home</a>
            <a href="#signup" class="smoothScroll">SignUp</a>
            <a href="#signin" class="smoothScroll">Login</a>
            <a href="#services" class="smoothScroll">Features</a>
            <a href="#pricing" class="smoothScroll">Pricing</a>
			<a href="#about" class="smoothScroll">About</a>
			<a href="#testimonials" class="smoothScroll">Testimonials</a>
			<a href="#contact" class="smoothScroll">Contact</a>
			<a href="#"><i class="icon-facebook"></i></a>
			<a href="#"><i class="icon-twitter"></i></a>
			<a href="#"><i class="icon-dribbble"></i></a>
			<a href="#"><i class="icon-envelope"></i></a>
		</div>
		
		<!-- Menu button -->
		<div id="menuToggle"><i class="icon-reorder"></i></div>
	</nav>


	
	<!-- ========== HEADER SECTION ========== -->
	<section id="home" name="home"></section>
	<div id="headerwrap">
		<div class="container">
			<br>
			<h1>RipperFit</h1>
			<h2>Resource Tracking in a Flash!!!</h2>
			<div class="row">
				<br>
				<br>
				<br>
				<div class="col-lg-6 col-lg-offset-3">
				</div>
			</div>
		</div><!-- /container -->
	</div><!-- /headerwrap -->
	
	
	<!-- ========== WHITE SECTION ========== -->
	<div id="w">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
				<h3>Welcome To <bold>RipperFit</bold>, <bold>A Resource Tracking Engine </bold> Brought To You By <bold>VARAAS</bold>. <br/>
					<bold>Ideal For Companies & Organisations!!!</bold> 
				</h3>
				</div>
			</div>
		</div><!-- /container -->
	</div><!-- /w -->
	
	<!-- ========== FEATURES - GREY SECTION ========== -->
	<section id="services" name="services"></section>
	<div id="g">
		<div class="container">
			<div class="row">
				<h3>FEATURES</h3>
				<br>
				<br>
				<div class="col-lg-3">
                  <!--  <span style="height:40px;width:40px;line-height:60px;border-color:transparent;border-width:0px;border-style:solid;background-color:#ffffff;border-radius:50%;">
                        <i class="fa fontawesome-icon fa-bolt circle-yes" style="border-color:#ffffff;border-width:0px;background-color:#ffffff;height:60px;width:60px;line-height:124px;border-radius:50%;border-width:0;position:relative;top:0px;left:0px;margin:0;border-radius:calc(50% - 0px);color:#333333;font-size:47px;"></i>
                     </span>-->
					<h4>Lightning Notifications</h4>
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever.</p>
				</div>
				<div class="col-lg-3">
					
					<h4>Resource Buddy</h4>
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever.</p>
				</div>
				<div class="col-lg-3">
					
					<h4>In-Depth Reporting</h4>
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever.</p>
				</div>
				<div class="col-lg-3">
					
					<h4>Splendid Dashboard</h4>
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever.</p>
				</div>
			</div>
		</div><!-- /container -->
	</div><!-- /g -->
	
	<!-- ========== CHARTS - DARK GREY SECTION ========== -->
<!--	<div id="dg">
		<div class="container">
			<div class="row">
				<h3>SOME CHARTS</h3>
				<br>
				<br>
				<div class="col-lg-4">
					<canvas id="javascript" height="130" width="130"></canvas>
					<br>
					<h4>Javascript</h4>
					<br>
					<script>
						var doughnutData = [
								{
									value: 70,
									color:"#f85c37"
								},
								{
									value : 30,
									color : "#ecf0f1"
								}
							];
							var myDoughnut = new Chart(document.getElementById("javascript").getContext("2d")).Doughnut(doughnutData);
					</script>

				</div>
				<div class="col-lg-4">
					<canvas id="bootstrap" height="130" width="130"></canvas>
					<br>
					<h4>Bootstrap</h4>
					<br>
					<script>
						var doughnutData = [
								{
									value: 90,
									color:"#f85c37"
								},
								{
									value : 10,
									color : "#ecf0f1"
								}
							];
							var myDoughnut = new Chart(document.getElementById("bootstrap").getContext("2d")).Doughnut(doughnutData);
					</script>
				</div>
				<div class="col-lg-4">
					<canvas id="wordpress" height="130" width="130"></canvas>
					<br>
					<h4>Wordpress</h4>
					<br>
					<script>
						var doughnutData = [
								{
									value: 55,
									color:"#f85c37"
								},
								{
									value : 45,
									color : "#ecf0f1"
								}
							];
							var myDoughnut = new Chart(document.getElementById("wordpress").getContext("2d")).Doughnut(doughnutData);
					</script>
				</div>
			
			</div>
		</div><!-- /container -->
	</div><!-- /dg -->
	
<!--	<section id="portfolio" name="portfolio"></section>
	<div id="portfoliowrap">
		<div class="container">
			<div class="row">
				<h3>COOL WORKS</h3>
				<br>
				<br>
				<div class="col-lg-4 port-space">
					<a href="item.html"><img src="assets/img/work1.png"></a>
				</div>
				<div class="col-lg-4 port-space">
					<a href="item.html"><img src="assets/img/work2.png"></a>
				</div>
				<div class="col-lg-4 port-space">
					<a href="item.html"><img src="assets/img/work3.png"></a>
				</div>				
			</div>	
		</div><!-- /container -->
	</div><!-- /portfoliowrap -->
	<!-- ========== WHITE SECTION ========== -->
	<div id="w">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
				<h3>We Work Hard To Deliver <bold>Transcendence Services</bold>. Our Core Aim Is Your Complete <bold>Satisfaction</bold>.
				</h3>
				</div>
			</div>
		</div><!-- /container -->
	</div><!-- /w -->
	
	<!-- ========== ABOUT - GREY SECTION ========== -->
	<section id="about" name="about"></section>
	<div id="g">
		<div class="container">
			<div class="row">
				<h3>ABOUT</h3>
				<br>    
				<div class="col-lg-2"></div>
				<div class="col-lg-8">
				<p>RipperFit lets you effortlessly track your resource request from bottom to top. It comes with the goal of serving as a complete resource management engine. Get instant notifications at every point, view your complete resource history as well as track all durations taken for resource allotment. Well, we are a gang of five members lead by a terrific leader, Aman. RipperFit is our very first gig. We care about quality, craft, feedback and disciplined experimentation.</p>
				<br>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="row">
                <h3>MEET THE TEAM</h3>
                <br>
                <br>
				<div class="col-lg-1"></div>
				<div class="col-lg-10">
					<div class="col-lg-4 team">
						<img class="img-circle" src="assets/img/aman.jpg" height="90" width="90">
						<h4>Aman Agarwal</h4>
						<h5><i>Product Manager</i></h5>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
						<p>
							<a href="index.html#"><i class="icon-facebook"></i></a>
							<a href="index.html#"><i class="icon-twitter"></i></a>
							<a href="index.html#"><i class="icon-envelope"></i></a>

						</p>
					</div>

					<div class="col-lg-4 team">
						<img class="img-circle" src="assets/img/amit.jpg" height="90" width="90">
						<h4>Amit Kumar</h4>
						<h5><i>Back-End Guru</i></h5>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
						<p>
							<a href="index.html#"><i class="icon-facebook"></i></a>
							<a href="index.html#"><i class="icon-twitter"></i></a>
							<a href="index.html#"><i class="icon-envelope"></i></a>

						</p>
					</div>

					<div class="col-lg-4 team">
						<img class="img-circle" src="assets/img/vaibhav.jpg" height="90" width="90">
						<h4>Vaibhav Pravin Zambad</h4>
						<h5><i>Back-End Guru</i></h5>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
						<p>
							<a href="index.html#"><i class="icon-facebook"></i></a>
							<a href="index.html#"><i class="icon-twitter"></i></a>
							<a href="index.html#"><i class="icon-envelope"></i></a>

						</p>
					</div>
				</div>
				<div class="col-lg-1"></div>
			</div>
			<div class="row">
				<div class="col-lg-1"></div>
				<div class="col-lg-10">
					<div class="col-lg-4 team">
						<img class="img-circle" src="assets/img/riya.jpg" height="90" width="90">
						<h4>Riya Nuwal</h4>
						<h5><i>Web Designer</i></h5>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
						<p>
							<a href="index.html#"><i class="icon-facebook"></i></a>
							<a href="index.html#"><i class="icon-twitter"></i></a>
							<a href="index.html#"><i class="icon-envelope"></i></a>

						</p>
					</div>

					<div class="col-lg-4 team">
						<img class="img-circle" src="assets/img/amita.jpg" height="90" width="90">
						<h4>Amita Sharma</h4>
						<h5><i>Web Designer</i></h5>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
						<p>
							<a href="index.html#"><i class="icon-facebook"></i></a>
							<a href="index.html#"><i class="icon-twitter"></i></a>
							<a href="index.html#"><i class="icon-envelope"></i></a>

						</p>
					</div>

					<div class="col-lg-4 team">
						<img class="img-circle" src="assets/img/shobhit.jpg" height="90" width="90">
						<h4>Shobhit Agarwal</h4>
						<h5><i>Front-end Developer</i></h5>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
						<p>
							<a href="index.html#"><i class="icon-facebook"></i></a>
							<a href="index.html#"><i class="icon-twitter"></i></a>
							<a href="index.html#"><i class="icon-envelope"></i></a>

						</p>
					</div>
				</div>
				<div class="col-lg-1"></div>
			</div>
		</div><!-- /container -->
	</div><!-- /g -->
	
	<!-- ========== FOOTER SECTION ========== -->
	<section id="contact" name="contact"></section>
	<div id="f">
		<div class="container">
			<div class="row">
					<h3><b>CONTACT US</b></h3>
					<br>
					<div class="col-lg-4">
						<h3><b>Send Us A Message:</b></h3>
						<h3>reachus@ripperfit.com</h3>
						<br>
					</div>
					
					<div class="col-lg-4">	
						<h3><b>Call Us:</b></h3>
						<h3>+91-9785057113</h3>
						<br>
					</div>
					
					<div class="col-lg-4">
						<h3><b>We Are Social</b></h3>
						<p>
							<a href="index.html#"><i class="icon-facebook"></i></a>
							<a href="index.html#"><i class="icon-twitter"></i></a>
							<a href="index.html#"><i class="icon-envelope"></i></a>
						</p>
						<br>
					</div>
				</div>
			</div>
		</div><!-- /container -->
	</div><!-- /f -->
	
	<div id="c">
		<div class="container">
			<p>Created by <a href="#">VARAAS</a></p>
		
		</div>
	</div>
	
	

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="assets/js/classie.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/smoothscroll.js"></script>
	<script src="assets/js/main.js"></script>
</body>
</html>
