<?PHP
$hostname="localhost";
$database="app";
$username="root";
$password="";
$json=array();
	if(isset($_GET["correo"]) && isset($_GET["pass"])){
		$correo=$_GET['correo'];
		$pass=$_GET['pass'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="SELECT correo, pass,nombre FROM usuarios WHERE correo= '{$correo}' AND pass = '{$pass}'";
		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){
		
			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}



		else{
			$results["correo"]='';
			$results["pass"]='';
			$results['nombre']='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{
		   	$results["correo"]='';
			$results["pass"]='';
			$results['nombre']='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>