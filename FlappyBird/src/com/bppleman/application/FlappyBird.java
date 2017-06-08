package com.bppleman.application;

import com.bppleman.game.GameController;
import com.bppleman.game.GameProtocol;
import com.bppleman.level.LevelController;
import com.bppleman.level.LevelProtocol;
import com.bppleman.setting.SettingController;
import com.bppleman.setting.SettingProtocol;
import com.bppleman.window.WindowController;

public class FlappyBird implements LevelProtocol, SettingProtocol, GameProtocol
{
    private WindowController windowController;
    private LevelController levelController;
    private SettingController settingController;
    private GameController gameController;

    public FlappyBird()
    {
        windowController = new WindowController();
        levelController = new LevelController(this);
        settingController = new SettingController(this);
        gameController = new GameController(this);
        windowController.setKeyView(levelController.getView());
        windowController.showWindow();
    }

    public void showSettingView()
    {
        windowController.setKeyView(settingController.getView());
    }

    public void showLevelView()
    {
        windowController.setKeyView(levelController.getView());
    }

    public void showGameView()
    {
        windowController.setKeyView(gameController.getView());
    }
    /*
    ============================================================================================
	关于LevelController的代理
	============================================================================================
	 */

    @Override public void shouldOpenSetting()
    {
        showSettingView();
        windowController.repaintWindow();
    }

    @Override public void shouldStartGameWithLevel(String level)
    {
        showGameView();
        windowController.setWindowBounds(gameController.getBackGroundImageSize());
        windowController.repaintWindow();
        gameController.initGame(level);
        gameController.startGame();
    }

    /*
    ============================================================================================
	关于SettingController的代理
	============================================================================================
	 */

    @Override public void shouldOpenLevel()
    {
        showLevelView();
        windowController.repaintWindow();
    }

	/*
	============================================================================================
	关于GameController的代理
	============================================================================================
	 */

    @Override public void didFinishedGame()
    {
        showLevelView();
        windowController.setWindowDefaultFrame();
        windowController.repaintWindow();
    }

    /**
     * 程序入口main函数
     *
     * @param args
     */
    public static void main(String[] args)
    {
        new FlappyBird();
    }
}
