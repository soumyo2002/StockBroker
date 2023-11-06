package Functionality;

public class Authentication {

	public boolean passwordAuth(String password) {
		// TODO Auto-generated method stub
		int len = password.length();
		    if (len <= 8) {
		        System.out.println("Password length must be greater than 8 characters");
		        return false;
		    } else if (!password.matches(".*[a-z].*")) {
		        System.out.println("Password must contain at least one lowercase letter");
		        return false;
		    } else if (!password.matches(".*[A-Z].*")) {
		        System.out.println("Password must contain at least one uppercase letter");
		        return false;
		    } else if (!password.matches(".*\\d.*")) {
		        System.out.println("Password must contain at least one number");
		        return false;
		    } else if (!password.matches(".*[!@#$%^&*].*")) {
		        System.out.println("Password must contain at least one special character (!, @, #, $, %, ^, &, *)");
		        return false;
		    } else if (password.matches(".*[\\s].*")) {
		        System.out.println("Password cannot contain any whitespace");
		        return false;
		    }
		    return true;
		    
		    //For more advanced check,we can use certain APIs that check for password strength
		}

	public boolean otpAuth(String email, boolean isEmail) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean panAuth(String pan) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean adhaarAuth(String adhaar) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean bankAuth(String IFSC, String mICR, long acc_no) {
		// TODO Auto-generated method stub
		return false;
	}

}
