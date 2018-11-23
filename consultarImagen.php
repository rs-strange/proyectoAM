<?PHP
$hostname_localhost ="localhost";
$database_localhost ="app";
$username_localhost ="root";
$password_localhost ="";
$json=array();
	if(isset($_GET["id_categoria"])){
		$documento=$_GET["id_categoria"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		$consulta="select * from categoria where id_categoria= '{$documento}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["id_categoria"]=$registro['id_categoria'];
			$result["nombre"]=$registro['nombre'];
			$result["profesion"]=$registro['profesion'];
			$result["imagen"]=base64_encode($registro['imagen']);
			$json['usuario'][]=$result;
		}else{
			$resultar["id_categoria"]=0;
			$resultar["nombre"]='no registra';
			$resultar["profesion"]='no registra';
			$resultar["imagen"]='no registra';
			$json['usuario'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['usuario'][]=$resultar;
		echo json_encode($json);
	}
?>