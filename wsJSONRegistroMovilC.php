<?PHP
$hostname_localhost="localhost";
$database_localhost="app";
$username_localhost="root";
$password_localhost="";
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	//$documento = $_POST["documento"];
	$nombre = $_POST["nombre"];
	//$profesion = $_POST["profesion"];
	$imagen = $_POST["imagen"];
	$categoria = $_POST["categoria"];
	$path = "imagenes/$nombre.jpg";
	//$url = "http://$hostname_localhost/ejemploBDRemota/$path";
	$url = "imagenes/".$nombre.".jpg";
	file_put_contents($path,base64_decode($imagen));
	$bytesArchivo=file_get_contents($path);
	$sql="INSERT INTO contenido(nombre,imagen,nombre_categoria) VALUES (?,?,?)";
	$stm=$conexion->prepare($sql);
	$stm->bind_param('sss',$nombre,$bytesArchivo,$categoria);
		
	if($stm->execute()){
		echo "registra";
	}else{
		echo "noRegistra";
	}
	
	mysqli_close($conexion);
?>