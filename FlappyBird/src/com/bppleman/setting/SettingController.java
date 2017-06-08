package com.bppleman.setting;

import com.bppleman.model.user.User;

import javax.swing.*;

/**
 * Created by BppleMan on 17/5/29.
 */
public class SettingController
{
    private SettingProtocol delegate;
    private JPanel mainView;
    private SettingAction settingAction;

    public SettingController(SettingProtocol delegate)
    {
        this.delegate = delegate;
        initAction();
        initMainView();
    }

    private void initMainView()
    {
        mainView = new SettingView(settingAction);
        mainView.setOpaque(false);
        mainView.setLayout(null);
    }

    private void initAction()
    {
        settingAction = new SettingAction(this);
    }

    public void loginButtonDoClick()
    {
        User.getInstance();
        //        String username = getUserName();
        //        String password = getPassWord();
        //        if (username != null && password != null)
        //        {
        //            User user = new User(username,password);
        //        }
    }

    public void registButtonDoClick()
    {
    }

    public void closeButtonDoClick()
    {
        delegate.shouldOpenLevel();
    }

    //    private String getUserName()
    //    {
    //        String name = usernameText.getText();
    //        if (name.equals("") || name == null)
    //        {
    //            JOptionPane.showMessageDialog(null,"用户名不能为空");
    //            return null;
    //        }
    //        return name;
    //    }
    //
    //    private String getPassWord()
    //    {
    //        String pwd = passwordText.getText();
    //        if (pwd.equals("") || pwd == null)
    //        {
    //            JOptionPane.showMessageDialog(null,"密码不能为空");
    //            return null;
    //        }
    //        return pwd;
    //    }

    public JPanel getView()
    {
        return mainView;
    }

}
