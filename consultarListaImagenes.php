<?PHP
$hostname_localhost ="localhost";
$database_localhost ="app";
$username_localhost ="root";
$password_localhost ="";
$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		$consulta="select * from categoria";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			//$result["id_categoria"]=$registro['id_categoria'];
			$result["nombre"]=$registro['nombre'];
			//$result["profesion"]=$registro['profesion'];
			$result["imagen"]=base64_encode($registro['imagen']);
			$json['usuario'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
?>