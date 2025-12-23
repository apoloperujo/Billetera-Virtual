package DAO;

public class DAOFactory {

    public static CriptoUsuarioDAOjdbc getCriptoUsuarioDAO() {
    	return new CriptoUsuarioDAOjdbc();
    }
    public static FiduciariaUsuarioDAOjdbc getFiduciariaUsuarioDAO() {
    	return new FiduciariaUsuarioDAOjdbc();
    }
    public static MonedaDAOjdbc getMonedaDAO() {
    	return new MonedaDAOjdbc();
    }
    public static TransaccionesDAOjdbc getTransaccionesDAO() {
    	return new TransaccionesDAOjdbc();
    }
}