
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun isValidPassword(password: String): Boolean {
  return  password.length>=8;
}
fun isNonEmpty(password: String): Boolean {
    return  password.isNotEmpty();
}