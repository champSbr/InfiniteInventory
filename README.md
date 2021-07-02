## Infinite Inventory
A lib where helps you to create infinite inventories in Minecraft (Pagination system).
With this lib, you can create a infinite inventory and set the slot you want to be the Previous/Next button and the slot you want to be the first and the list containing items.

## Usage

    new InfiniteInventory(List<ItemStack>, Player, "Inventory Name", Number of Rows, First Item Slot, Last Item Slot, Previous Button Slot, Next Button Slot);
Example:

    @EventHandler  
	public void onChat(PlayerCommandPreprocessEvent e) {  
	    if(e.getMessage().contains("menu")) {  
	        e.setCancelled(true);  
			List<ItemStack> itens = new ArrayList<>();  
			for(int i=0; i <25 ;i++) {  
	            ItemStack is = new ItemStack(Material.ARROW);  
			    itens.add(is);  
			}  
	        new InfiniteInventory(itens,e.getPlayer(),"§8Teste",3,10,16,9,17);  
			return;  
		}  
	}

You can modify the ItemMeta of the buttons in the lib class.
