package authorization;

public class TestEmailValidate {
	public static void main(String[] args) {
		System.out.println("Diesel31ks@mail.ru = "+EmailValidate.validateEmail("Diesel31ks@mail.ru"));
		System.out.println("Diesel31ks@.ru = "+EmailValidate.validateEmail("Diesel31ks@.ru"));
		System.out.println("Diesel31ks@mail.ruasd = "+EmailValidate.validateEmail("Diesel31ks@mail.ruasd"));
		System.out.println("Diesel31ksmail.ru = "+EmailValidate.validateEmail("Diesel31ksmail.ru"));
		System.out.println("23123@mail.ru = "+EmailValidate.validateEmail("23123@mail.ru"));
	}
}
