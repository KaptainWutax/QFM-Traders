package kaptainwutax.traders.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaptainwutax.traders.Product;
import kaptainwutax.traders.Trade;
import kaptainwutax.traders.config.ConfigTrades;
import kaptainwutax.traders.util.Pair;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InitTrade {

	public static List<Trade> TOM = new ArrayList<Trade>();
	public static List<Trade> BOBBY = new ArrayList<Trade>();
	
	public static void registryTrades() {
		registryTrade(TOM, InitConfig.CONFIG_TOM);
		registryTrade(BOBBY, InitConfig.CONFIG_BOBBY);
	}
	
	public static void registryTrade(List<Trade> trades, ConfigTrades config) {
		//Add the trades in config.
		for(Trade trade: config.CUSTOM_TRADES) {	
			if(!trade.isValid())continue;
			
			for(Product disallowed: config.BLACKLIST) {
				if(trade.hasProduct(disallowed))continue;
			}

			trades.add(trade);
		}		
		
		Trade defaultTrade = config.DEFAULT_TRADE;		
		if(defaultTrade == null || defaultTrade.getBuy() == null || defaultTrade.getSell() == null)return;
		
		//Add the rest of the items in the game, with default value.
		for(Item item: Item.REGISTRY) {
			if(item.getCreativeTab() == null)continue;
			
			NonNullList<ItemStack> items = NonNullList.create();			
			item.getSubItems(item.getCreativeTab(), items);
			
			for(ItemStack metaItem: items) {
				Trade trade = new Trade(
						new Product(metaItem.getItem(), metaItem.getMetadata(), defaultTrade.getBuy().getAmount()), 
						null, 
						new Product(defaultTrade.getSell().getItem(), defaultTrade.getSell().getMetadata(), defaultTrade.getSell().getAmount()),
						defaultTrade.getMaxUses()
				);
				
				if(!trade.isValid())continue;
				
				for(Product disallowed: config.BLACKLIST) {
					if(trade.hasProduct(disallowed))continue;
				}
				
				trades.add(trade);
			}
		}
	}
	
}
