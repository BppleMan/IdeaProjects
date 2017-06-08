package com.flappybirdmap.editor;

import com.apple.eawt.AppEvent;
import com.apple.eawt.Application;
import com.apple.eawt.QuitHandler;
import com.apple.eawt.QuitResponse;
import com.flappybirdmap.attribute.AttributeBarController;
import com.flappybirdmap.attribute.AttributeBarDelegate;
import com.flappybirdmap.document.DocumentController;
import com.flappybirdmap.event.FileEvent;
import com.flappybirdmap.layout.MyBorderLayout;
import com.flappybirdmap.menu.MenuController;
import com.flappybirdmap.menu.MenuControllerDelegate;
import com.flappybirdmap.tool.ToolBarController;

import javax.swing.*;
import java.awt.*;

public class FlappyBirdMapEditor implements MenuControllerDelegate, AttributeBarDelegate, QuitHandler
{
    private Application mainApp;
    private MapEditorWindow mainWindow;
    private MenuController menuController;
    private AttributeBarController attributeBarController;
    private DocumentController documentController;
    private ToolBarController toolBarController;
    private FileEvent fileEvent;

    public FlappyBirdMapEditor()
    {
        initApplication();
        initWindow();
        initMenuController();
        initDocumentController();
        initToolBarController();
        initAttributeBarController();
        mainWindow.setJMenuBar(menuController.getMenuBar());
        mainWindow.getContentPane().add(toolBarController.getView(), MyBorderLayout.WEST);
        mainWindow.getContentPane().add(documentController.getView(), MyBorderLayout.CENTER);
        mainWindow.getContentPane().add(attributeBarController.getView(), MyBorderLayout.NORTH);
        documentController.addPipeListener(attributeBarController);
        showWindow();
    }

    private void initApplication()
    {
        mainApp = Application.getApplication();
        mainApp.setQuitHandler(this);
    }

    private void initWindow()
    {
        mainWindow = new MapEditorWindow();
        mainWindow.setDefaultCloseOperation(MapEditorWindow.HIDE_ON_CLOSE);
        mainWindow.setLayout(new MyBorderLayout());
        mainWindow.setTitle("Untitled");
    }

    private void initMenuController()
    {
        menuController = new MenuController(this);
    }

    private void initToolBarController()
    {
        toolBarController = new ToolBarController();
    }

    private void initDocumentController()
    {
        documentController = new DocumentController(null);
    }

    private void initAttributeBarController()
    {
        attributeBarController = new AttributeBarController(this);
    }

    private void showWindow()
    {
        mainWindow.setVisible(true);
        documentController.getView().requestFocus();
    }

    /**
     * 关于MenuController的代理方法
     */
    @Override public void shouldSaveDocumentFile()
    {
        if (fileEvent == null)
        {
            FileDialog fileDialog = new FileDialog(new JFrame(), "保存地图文件", FileDialog.SAVE);
            fileDialog.setVisible(true);
            String parent = fileDialog.getDirectory();
            String child = fileDialog.getFile();
            if (parent != null && child != null)
            {
                fileEvent = new FileEvent(parent, child);
            }
            mainWindow.setTitle(child);
        }
        documentController.saveDocument(fileEvent);
    }

    /*
     * （非 Javadoc）
     *
     * @see
     * com.flappybirdmap.menu.MenuControllerDelegate#shouldOpenDocumentFile()
     */
    @Override public void shouldOpenDocumentFile()
    {
        FileDialog fileDialog = new FileDialog(new JFrame(), "打开地图文件", FileDialog.LOAD);
        fileDialog.setVisible(true);
        String parent = fileDialog.getDirectory();
        String child = fileDialog.getFile();
        if (parent != null && child != null)
        {
            fileEvent = new FileEvent(parent, child);
            mainWindow.setTitle(child);
        }
        documentController.openDocument(fileEvent);
    }

    /*
     * 关于AttributeBarController的代理方法
     */
    @Override public void shouldRemoveFocus(AttributeBarController controller)
    {
        documentController.acceptFocus();
    }

    @Override public void shouldRepaintPipe(double x, double y) throws NullPointerException
    {
        documentController.moveSelectedPipeToNewPoint(x, y);
    }

    /**
     * 关于setter、getter方法
     */
    public MapEditorWindow getMainWindow()
    {
        return mainWindow;
    }

    public MenuController getMenuController()
    {
        return menuController;
    }

    public AttributeBarController getAttributeBarController()
    {
        return attributeBarController;
    }

    public DocumentController getWorkController()
    {
        return documentController;
    }

    public ToolBarController getToolBarController()
    {
        return toolBarController;
    }

    public static void main(String[] args)
    {
        new FlappyBirdMapEditor();
    }

    @Override public void handleQuitRequestWith(AppEvent.QuitEvent quitEvent, QuitResponse quitResponse)
    {
        int index = documentController.documentWillClose();
        switch (index)
        {
            case -1:
                quitResponse.performQuit();
                break;
            case 0:
                shouldSaveDocumentFile();
                quitResponse.performQuit();
                break;
            case 1:
                quitResponse.performQuit();
                break;
            case 2:
                quitResponse.cancelQuit();
                break;
        }
    }
}
