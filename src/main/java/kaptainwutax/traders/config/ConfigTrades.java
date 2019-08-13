package kaptainwutax.traders.config;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import kaptainwutax.traders.Product;
import kaptainwutax.traders.Trade;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public abstract class ConfigTrades extends Config {
	
	@Expose public Trade DEFAULT = null;
	@Expose public List<Trade> TRADES = new ArrayList<Trade>();
	@Expose public List<Product> BLACKLIST = new ArrayList<Product>();
	
	public ConfigTrades(Trade defaultTrade) {
		this.DEFAULT = defaultTrade;
	}
	
}
