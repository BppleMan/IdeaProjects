package com.flappybirdmap.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuController
{
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openFileItem;
    private JMenuItem savaFileItem;
    // private FileDialog fileDialog;
    // private DirectoryChooser directoryChooser;
    // private MapEditorWindow mapEditorWindow;
    private MenuAction menuAction;
    private MenuControllerDelegate delegate;

    public MenuController(MenuControllerDelegate delegate)
    {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        this.delegate = delegate;
        menuBar = new JMenuBar();
        fileMenu = new JMenu("文件");
        openFileItem = new JMenuItem("打开");
        savaFileItem = new JMenuItem("保存");

        fileMenu.add(openFileItem);
        fileMenu.add(savaFileItem);
        menuBar.add(fileMenu);
        initHotKey();
        initMenuAction();
    }

    private void initMenuAction()
    {
        menuAction = new MenuAction(this);
        openFileItem.addActionListener(menuAction);
        savaFileItem.addActionListener(menuAction);
    }

    private void initHotKey()
    {
        // 快捷键设置
        int KEY = 0;
        String vers = System.getProperty("os.name").toLowerCase();
        if (vers.indexOf("windows") != -1)
        {
            KEY = Event.CTRL_MASK;
        }
        else if (vers.indexOf("mac") != -1)
        {
            KEY = Event.META_MASK;
        }
        Toolkit tk = Toolkit.getDefaultToolkit();
        openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, tk.getMenuShortcutKeyMask()));
        savaFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, tk.getMenuShortcutKeyMask()));
    }

    protected void openFileDidClick()
    {
        delegate.shouldOpenDocumentFile();
    }

    protected void saveFileDidClick()
    {
        delegate.shouldSaveDocumentFile();
    }

    public JMenuBar getMenuBar()
    {
        return menuBar;
    }

    public JMenuItem getOpenFileItem()
    {
        return openFileItem;
    }

    public JMenuItem getSavaFileItem()
    {
        return savaFileItem;
    }
}
