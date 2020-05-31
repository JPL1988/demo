package com.example.demo.result;

/**
 * @author false
 * @date 20/5/29 14:40
 */
public class LoginResult {
    private boolean success;
    private String name;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginResult(boolean success, String name,String msg) {
        this.success = success;
        this.name = name;
        this.msg=msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean flag) {
        this.success = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "flag=" + success +
                ", name='" + name + '\'' +
                ", msg='" + msg +
                '}';
    }
}
