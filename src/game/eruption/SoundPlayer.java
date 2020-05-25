package game.eruption;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Sound;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
/* This class is used to play sound for the game */
public class SoundPlayer 
{
	public static Map<String,Sound> soundmap = new HashMap<String,Sound>();
	public static Map<String,Music> musicmap = new HashMap<String,Music>();
	/* This method loads our .wav or .ogg files 
	 * in to our map which stores the music files
	 * and when every we need to access these files
	 * we can access them by giving the key and getting the
	 * file we need */
	public static void load()
	{
		try
		{
			soundmap.put("bronze_coin", new Sound("pickup_coin.wav"));
			soundmap.put("gold_coin", new Sound("gold_coin.wav"));
			soundmap.put("silver_coin", new Sound("sliver_coin.wav"));
			soundmap.put("fire_ball", new Sound("fire_ball.wav"));
			soundmap.put("magma_rock", new Sound("magma_rock.wav"));
			soundmap.put("bomb", new Sound("bomb.wav"));
			soundmap.put("water_bucket", new Sound("water_bucket.wav"));
			soundmap.put("water_bucket_pickUp", new Sound("water_bucket_pickUp.wav"));
			soundmap.put("key_pickUp", new Sound("key_pickUp.wav"));
			soundmap.put("pistol_shoot", new Sound("pistol_bullet.wav"));
			soundmap.put("shotgun_shoot", new Sound("shotgun_bullet.wav"));
			soundmap.put("dualpistol_shoot", new Sound("dualpistol_bullet.wav"));
			soundmap.put("sniper_shoot", new Sound("sniper_bullet.wav"));
			soundmap.put("egg_cracking", new Sound("egg_cracking.wav"));
			soundmap.put("freeze_potion", new Sound("freeze_potion.wav"));
			soundmap.put("diamond_pickUp", new Sound("diamond_pickUp.wav"));
			soundmap.put("ruby_pickUp", new Sound("ruby_pickUp.wav"));
			soundmap.put("crate_pickUp", new Sound("crate_pickUp.wav"));
			soundmap.put("under_explosion", new Sound("undergroundenemy_explosion.wav"));
			soundmap.put("enemyBullet", new Sound("enemyBullet.wav"));
			soundmap.put("wizard", new Sound("wizard.wav"));
			soundmap.put("golden_rod", new Sound("golden_rod.wav"));
			soundmap.put("emerald", new Sound("emerald.wav"));
			soundmap.put("boss_arrow", new Sound("boss_arrow.wav"));
			soundmap.put("pink_gem", new Sound("pink_gem.wav"));
			soundmap.put("health_potion", new Sound("health_potion.wav"));
			soundmap.put("exploder_enemy", new Sound("exploder_enemy.wav"));
			soundmap.put("ray_bullet", new Sound("ray_bullet.wav"));
			soundmap.put("raybullet_damage", new Sound("raybullet_damage.wav"));
			soundmap.put("rocket_move", new Sound("rocket_move.wav"));
			soundmap.put("rocket_impact", new Sound("rocket_impact.wav"));
			soundmap.put("dragon_call", new Sound("dragon_call.wav"));
			soundmap.put("purple_emerald", new Sound("purple_emerald.wav"));
			soundmap.put("tank_bullet", new Sound("tank_bullet.wav"));
			soundmap.put("tankbullet_release", new Sound("tankbullet_release.wav"));
			soundmap.put("boomerang", new Sound("boomerang.wav"));
			soundmap.put("shieldenemy_shoot", new Sound("shieldenemy_shoot.wav"));
			soundmap.put("boss_rage", new Sound("boss_rage.wav"));
			soundmap.put("boss_damage", new Sound("boss_damage.wav"));
			soundmap.put("mouse_clicked", new Sound("mouse_clicked.wav"));
			musicmap.put("game_music",new Music("Unavailable.wav"));
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	/* This method allows us to get our
	 * sound from the soundmap HashMap
	 * it is a getter method */
	public static Sound getSound(String key) 
	{
		return soundmap.get(key);
	}
	/* This method allows us to get our
	 * sound from the musicmap HashMap
	 * it is a getter method */
	public static Music getMusic(String key) 
  {
		return musicmap.get(key);
	}
}
