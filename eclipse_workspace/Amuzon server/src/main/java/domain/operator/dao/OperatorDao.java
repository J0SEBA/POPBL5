package main.java.domain.operator.dao;


import main.java.domain.login.model.Login;
import main.java.domain.operator.model.Operator;

public interface OperatorDao {
	public Operator validateOperator(Login login);
}

