<?PHP
$hostname="localhost";
$database="app";
$username="root";
$password="";
$json=array();
	if(isset($_GET["nombre"])&&($_GET["apellidoM"]) && isset($_GET["apellidoP"])
	 && isset($_GET["correo"]) && isset($_GET["pass"]))
	{
		$nombre=$_GET['nombre'];
		$apellidoM=$_GET['apellidoM'];
		$apellidoP=$_GET['apellidoP'];
		$correo=$_GET['correo'];
		$pass=$_GET['pass'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="INSERT INTO usuarios(nombre,apellidoM,apellidoP,correo,pass) 
		VALUES ('{$nombre}','{$apellidoP}' , '{$apellidoM}', '{$correo}', '{$pass}')";
		$resultado=mysqli_query($conexion,$consulta);
       
		if($consulta){
		   $consulta="SELECT * FROM usuarios  WHERE correo='{$correo}'";
		   $resultado=mysqli_query($conexion,$consulta);

			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}



		else{
			$results["nombre"]='';
			$results["apellidoM"]='';
			$results["apellidoP"]='';
			$results["correo"]='';
			$results["pass"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{   
		    $results["nombre"]='';
			$results["apellidoM"]='';
			$results["apellidoP"]='';
			$results["correo"]='';
			$results["pass"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>





