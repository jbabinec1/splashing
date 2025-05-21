package com.splashtracker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import net.runelite.api.Skill;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

public class SplashTrackerOverlay extends Overlay
{
    private final Client client;
    private final PanelComponent panelComponent = new PanelComponent();
    private int xpGained;

    @Inject
    private SplashTrackerOverlay(Client client)
    {
        this.client = client;
        setPosition(OverlayPosition.TOP_LEFT);
        setPriority(OverlayPriority.LOW);
    }

    public void setXpGained(int xp)
    {
        this.xpGained = xp;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        int currentXp = client.getSkillExperience(Skill.MAGIC);
        int nextLevelXp = Experience.getXpForLevel(client.getRealSkillLevel(Skill.MAGIC) + 1);
        int xpRemaining = nextLevelXp - currentXp;
        int xpPerCast = 17; // Fire Strike XP per cast (example)
        int castsRemaining = xpPerCast > 0 ? xpRemaining / xpPerCast : 0;

        panelComponent.getChildren().clear();
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Splash Tracker")
                .right("")
                .build());


        panelComponent.getChildren().add(LineComponent.builder()
                .left("XP Gained:")
                .right(String.valueOf(xpGained))
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left("XP Left:")
                .right(String.valueOf(xpRemaining))
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Casts Left:")
                .right(String.valueOf(castsRemaining))
                .build());

        return panelComponent.render(graphics);
    }
}

