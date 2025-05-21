package com.splashtracker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface SplashTrackerConfig extends Config
{
	@ConfigItem(
		keyName = "splash",
		name = "Splash Counter",
		description = "Keep track of splashing"
	)
	default String greeting()
	{
		return "Hello";
	}
}
