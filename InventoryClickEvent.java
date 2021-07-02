package me.champs.inv;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
            Player p = (Player) e.getWhoClicked();
            if(!InfiniteInventory.users.containsKey(p.getUniqueId())) return;
            InfiniteInventory inv = InfiniteInventory.users.get(p.getUniqueId());
            if(e.getCurrentItem() == null) return;
            if(e.getCurrentItem().getItemMeta() == null) return;
            if(e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aPróxima página")) { //name of next page button
                e.setCancelled(true);
                if(inv.currentPage >= inv.pages.size()-1){
                    return;
                }
                inv.currentPage += 1;
                p.openInventory(inv.pages.get(inv.currentPage));
                return;
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cPágina anterior")) { // name of previous page button
                e.setCancelled(true);
                if(inv.currentPage > 0){
                    inv.currentPage -= 1;
                    p.openInventory(inv.pages.get(inv.currentPage));
                    return;
                }
            }
    }

}
