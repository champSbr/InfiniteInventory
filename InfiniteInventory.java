package me.champs.inv;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InfiniteInventory {

    private Inventory inv;
    public int currentPage = 0;
    public int totalPages = 0;
    public static HashMap<UUID, InfiniteInventory> users = new HashMap<>();
    public List<Inventory> pages = new ArrayList<>();

    public InfiniteInventory(List<ItemStack> items, Player p, String name, int rows, int firstItemSlot, int lastItemSlot, int slotPreviousButton, int slotNextButton) {
        String rightName = name.replaceAll("&","§");
        inv = Bukkit.createInventory(null,9*rows,rightName + " - Pag. 1");
        int index = firstItemSlot;
        int totalItemsPerInventory = lastItemSlot-firstItemSlot+1;
        int totalItens = 0;
        int finalItemAmount = items.size();
        totalPages = finalItemAmount/totalItemsPerInventory;
        List<ItemStack> itemsToRemove = new ArrayList<>();
        if(items.size() > totalItemsPerInventory) {
            if(currentPage == 0) {
                ItemStack next = new ItemStack(Material.ARROW);
                ItemMeta meta2 = next.getItemMeta();
                meta2.setDisplayName("§aPróxima página");
                next.setItemMeta(meta2);
                inv.setItem(slotNextButton,next);
            }
            for(ItemStack is : items) {
                finalItemAmount++;
                if(totalItens == totalItemsPerInventory) {
                    pages.add(inv);
                    if(pages.size() == (int)totalPages)  {
                        inv = createInv(rows, rightName+" - Pag. " + Integer.valueOf(pages.size()+1), slotPreviousButton, slotNextButton, true, false);
                    } else {
                        inv = createInv(rows, rightName+" - Pag. " + Integer.valueOf(pages.size()+1), slotPreviousButton, slotNextButton, true, true);
                    }
                    index=firstItemSlot;
                    inv.setItem(index,is);
                    index++;
                    totalItens=1;
                } else {
                    inv.setItem(index,is);
                    index++;
                    totalItens++;
                }
                itemsToRemove.remove(is);
            }
            for(ItemStack is : itemsToRemove) {
                items.remove(is);
            }
        } else {
            for (ItemStack is : items) {
                inv.setItem(index, is);
                index++;
            }
        }
        pages.add(inv);
        p.openInventory(pages.get(currentPage));
        users.put(p.getUniqueId(), this);
    }


    public Inventory createInv(int rows, String name, int slotPreviousButton, int slotNextButton, boolean prevButton, boolean nextButton) {
        String rightName = name.replaceAll("&","§");
        Inventory inv = Bukkit.createInventory(null, 9 * rows, rightName);;

        ItemStack previous = new ItemStack(Material.ARROW);
        ItemMeta meta1 = previous.getItemMeta();
        meta1.setDisplayName("§cPágina anterior");
        previous.setItemMeta(meta1);

        ItemStack next = new ItemStack(Material.ARROW);
        ItemMeta meta2 = next.getItemMeta();
        meta2.setDisplayName("§aPróxima página");
        next.setItemMeta(meta2);

        if(prevButton) { inv.setItem(slotPreviousButton,previous); }
        if(nextButton) { inv.setItem(slotNextButton,next); }
        return inv;
    }

}
