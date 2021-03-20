# SpigotCustomItemsAPI
Example of admin sword:
```java
public class AdminSword extends CustomItem implements IItemEntityHit{

	private static AdminSword inst;
	public AdminSword() {
		super(new NamespacedKey(ExNihiloMain.getPlugin(ExNihiloMain.class), "admin_sword"));
		CustomItemStorage.getInstance().registerCustomItem(this);
		AdminSword.inst = this;
	}
  
	@Override
	public ItemStack create(int count) {
		ItemStack is = ItemBuilder.create(getMaterial(), count, "Admin sword", getCustomModelData(), "Insta-kill sword");
		ItemMeta im = is.getItemMeta();
		im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attack_damage", 2028, Operation.ADD_NUMBER));
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		is.setItemMeta(im);
		return is;
	}

	@Override
	public Material getMaterial() {
		// TODO Auto-generated method stub
		return Material.DIAMOND_SWORD;
	}
	
	public static AdminSword getInstance() {
		return inst;
	}

	@Override
	public void onHit(EntityDamageByEntityEvent event) {
		event.getDamager().sendMessage("lightning");
		Location loc = event.getEntity().getLocation();
		loc.getWorld().strikeLightningEffect(loc);
	}

}
```
