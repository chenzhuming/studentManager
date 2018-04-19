package action;

import com.opensymphony.xwork2.ModelDriven;
import entity.Users;
import org.apache.struts2.interceptor.validation.SkipValidation;
import services.UsersDAO;
import services.impl.UsersDAOImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users>{
    Users users = new Users();
    public String login(){
        UsersDAO usersDAO = new UsersDAOImpl();
        if(usersDAO.usersLogin(users)){
            session.setAttribute("loginUserName",users.getUsername());
            return SUCCESS;
        }else{
            return ERROR;
        }
    }
    @SkipValidation
    public String logout(){
        return "logout";
    }
    @Override
    public void validate() {
        super.validate();
        if("".equals(users.getUsername())){
            this.addFieldError("usernameError","用户名不能为空");
        }
        if(users.getPassword().length()<8){
            this.addFieldError("passwordError","密码长度不能小于8位");
        }
    }

    @Override
    public Users getModel() {
        return users;
    }
}