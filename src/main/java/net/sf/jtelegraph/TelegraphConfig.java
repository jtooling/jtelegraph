/**
 * JTelegraph -- a Java message notification library
 * Copyright (c) 2012, Paulo Roberto Massa Cereda
 * All rights reserved.
 *
 * Redistribution and  use in source  and binary forms, with  or without
 * modification, are  permitted provided  that the  following conditions
 * are met:
 *
 * 1. Redistributions  of source  code must  retain the  above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form  must reproduce the above copyright
 * notice, this list  of conditions and the following  disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither  the name  of the  project's author nor  the names  of its
 * contributors may be used to  endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS  PROVIDED BY THE COPYRIGHT  HOLDERS AND CONTRIBUTORS
 * "AS IS"  AND ANY  EXPRESS OR IMPLIED  WARRANTIES, INCLUDING,  BUT NOT
 * LIMITED  TO, THE  IMPLIED WARRANTIES  OF MERCHANTABILITY  AND FITNESS
 * FOR  A PARTICULAR  PURPOSE  ARE  DISCLAIMED. IN  NO  EVENT SHALL  THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE  LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY,  OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT  NOT LIMITED  TO, PROCUREMENT  OF SUBSTITUTE  GOODS OR  SERVICES;
 * LOSS  OF USE,  DATA, OR  PROFITS; OR  BUSINESS INTERRUPTION)  HOWEVER
 * CAUSED AND  ON ANY THEORY  OF LIABILITY, WHETHER IN  CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY  OUT  OF  THE USE  OF  THIS  SOFTWARE,  EVEN  IF ADVISED  OF  THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * TelegraphConfig.java: This class holds the telegraph configuration.
 */

// package definition
package net.sf.jtelegraph;

// needed packages
import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Holds the telegraph configuration.
 * @author Paulo Roberto Massa Cereda
 * @version 2.0
 * @since 2.0
 */
public class TelegraphConfig {
    
    // lots of configurations
    private Font titleFont;
    private Font descriptionFont;
    private Color titleColor;
    private Color descriptionColor;
    private ImageIcon backgroundImage;
    private Color backgroundColor;
    private TelegraphType icon;
    private Color borderColor;
    private int borderThickness;
    private long inDuration;
    private long outDuration;
    private long duration;
    private boolean enableButton;
    private String buttonCaption;
    private ImageIcon buttonIcon;
    private boolean stopOnMouseOver;
        
    // distance from screen
    private int distanceFromScreen;
    
    // the animation position
    private TelegraphPosition telegraphPosition;
    
    // the screen size
    private double screenWidth;
    private double screenHeight;
    
    
    // the window size
    private int windowWidth;
    private int windowHeight;

    // Getters and setters

    public boolean hasToStopOnMouseOver() {
        return stopOnMouseOver;
    }

    public void setStopOnMouseOver(boolean stopOnMouseOver) {
        this.stopOnMouseOver = stopOnMouseOver;
    }
    
    public String getButtonCaption() {
        return buttonCaption;
    }

    public void setButtonCaption(String buttonCaption) {
        this.buttonCaption = buttonCaption;
    }

    public boolean hasEnableButton() {
        return enableButton;
    }

    public void enableButton(boolean enableButton) {
        this.enableButton = enableButton;
    }

    public ImageIcon getButtonIcon() {
        return buttonIcon;
    }

    public void setButtonIcon(ImageIcon buttonIcon) {
        this.buttonIcon = buttonIcon;
    }
    
    protected void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    protected void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    } 

    public void setTelegraphPosition(TelegraphPosition telegraphPosition) {
        this.telegraphPosition = telegraphPosition;
    }

    public void setDistanceFromScreen(int distanceFromScreen) {
        this.distanceFromScreen = distanceFromScreen;
    }   

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getInDuration() {
        return inDuration;
    }

    public void setInDuration(long inDuration) {
        this.inDuration = inDuration;
    }

    public long getOutDuration() {
        return outDuration;
    }

    public void setOutDuration(long outDuration) {
        this.outDuration = outDuration;
    }
    
    /**
     * Default constructor.
     */
    public TelegraphConfig() {
        
        // set everything
        titleFont = null;
        descriptionFont = null;
        titleColor = Color.BLACK;
        descriptionColor = Color.BLACK;
        backgroundColor = Color.WHITE;
        backgroundImage = null;
        icon = TelegraphType.MESSAGE;
        borderColor = Color.BLACK;
        borderThickness = 2;
        inDuration = 250;
        outDuration = 250;
        duration = 2000;
        distanceFromScreen = 20;
        telegraphPosition = TelegraphPosition.TOP_LEFT;
        enableButton = false;
        buttonCaption = "OK";
        buttonIcon = null;
        stopOnMouseOver = false;
        
        {
            // get the screen size
            Rectangle rect = getScreenResolution();
            
            // set it
            screenWidth = rect.getWidth();
            screenHeight = rect.getHeight();
        }
        
    }  
    
    // Getters and setters
    
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
    }

    public Color getDescriptionColor() {
        return descriptionColor;
    }

    public void setDescriptionColor(Color descriptionColor) {
        this.descriptionColor = descriptionColor;
    }

    public Font getDescriptionFont() {
        return descriptionFont;
    }

    public void setDescriptionFont(Font descriptionFont) {
        this.descriptionFont = descriptionFont;
    }

    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/net/sf/jtelegraph/images/" + icon.toString().toLowerCase() + ".png"));
    }

    public void setIcon(TelegraphType icon) {
        this.icon = icon;
    }

    public Color getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }
    
    /**
     * Calculates the initial coordinates.
     * @return A point.
     */
    protected Point getInitialCoordinates() {
        
        // the points
        int positionX;
        int positionY;
        
        // check the option
        switch (telegraphPosition) {
            case BOTTOM_CENTER:
                positionX = (int) ((screenWidth / 2) - (windowWidth / 2));
                positionY = (int) (screenHeight + windowHeight);
                break;
            case TOP_CENTER:
                positionX = (int) ((screenWidth / 2) - (windowWidth / 2));
                positionY = (int) (-windowHeight);
                break;
            case TOP_LEFT:
                positionX = (int) (-windowWidth);
                positionY = (int) (-windowHeight);
                break;
            case BOTTOM_LEFT:
                positionX = (int) (-windowWidth);
                positionY = (int) (screenHeight);
                break;
            case TOP_RIGHT:
                positionX = (int) (screenWidth);
                positionY = (int) (-windowHeight);
                break;
            case BOTTOM_RIGHT:
                positionX = (int) (screenWidth);
                positionY = (int) (screenHeight + windowHeight);
                break;
            default:
                    positionX = 0;
                    positionY = 0;
        }
        
        // return new point
        return new Point(positionX, positionY);
    }
    
    /**
     * Calculates the final coordinates.
     * @return The point.
     */
    protected Point getFinalCoordinates() {
        
        // the points
        int positionX;
        int positionY;
        
        // check the option
        switch (telegraphPosition) {
            case BOTTOM_CENTER:
                positionX = (int) ((screenWidth / 2) - (windowWidth / 2));
                positionY = (int) ((screenHeight - windowHeight) - distanceFromScreen);
                break;
            case TOP_CENTER:
                positionX = (int) ((screenWidth / 2) - (windowWidth / 2));
                positionY = (int) (distanceFromScreen);
                break;
            case TOP_LEFT:
                positionX = (int) (distanceFromScreen);
                positionY = (int) (distanceFromScreen);
                break;
            case BOTTOM_LEFT:
                positionX = (int) (distanceFromScreen);
                positionY = (int) ((screenHeight - windowHeight) - distanceFromScreen);
                break;
            case TOP_RIGHT:
                positionX = (int) ((screenWidth - windowWidth) - distanceFromScreen);
                positionY = (int) (distanceFromScreen);
                break;
            case BOTTOM_RIGHT:
                positionX = (int) ((screenWidth - windowWidth) - distanceFromScreen);
                positionY = (int) ((screenHeight - windowHeight) - distanceFromScreen);
                break;                
            default:
                    positionX = 0;
                    positionY = 0;
        }
        
        // return the new point
        return new Point(positionX, positionY);
    }

    /**
     * Gets the screen resolution.
     * @return The screen resolution.
     */
    private Rectangle getScreenResolution() {
        
        // get the environment
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        // return the bounds
        return environment.getMaximumWindowBounds();
    }
   
}