package com.rezolt;

import java.text.DecimalFormat;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class main extends PluginBase implements Listener{
	@Override
	public void onEnable()
	{
		this.getLogger().info(TextFormat.GREEN + "Hud has been enabled!");
		this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
		
	}
	@Override
	public void onDisable()
	{
		this.getLogger().info(TextFormat.RED + "Hud has been disabled...");
		
	}
	Server server = this.getServer();
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();

  		DecimalFormat df = new DecimalFormat("#");
		String x = df.format(player.getPosition().x);
		String y = df.format(player.getPosition().y);
		String z = df.format(player.getPosition().z);
		String name = server.getName();
		String msg = TextFormat.colorize(this.getConfig().getString("Format")
                .replace("%x%", x)
                .replace("%y%", y)
        		.replace("%z%", z)
        		.replace("%servername%", name)
        		.replace("%playercount%", "")
        		);
				
				
				
				
				
		String type = this.getConfig().getString("Type");

		if(type.toLowerCase().contains("popup"))
		{
		player.sendPopup(msg);
		}
		else if(type.toLowerCase().contains("actionbar"))
		{
			player.sendActionBar(msg
	                .replace("%x%", x)
	                .replace("%y%", y)
	        		.replace("%z%", z));
			
		}
	}

}
