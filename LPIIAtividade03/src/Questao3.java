
public class Questao3 {

	public static void main(String[] args) {
		
		Login login = new Login("User", "Passw0rd");
		
		System.out.println(login.fazerLogin("Tesla", "StrongPwd"));
		System.out.println(login.fazerLogin("Cthulhu", "Passw0rd"));
		System.out.println(login.fazerLogin("User", "Wolverine"));
		System.out.println(login.fazerLogin("User", "Passw0rd"));
		
	}

}
