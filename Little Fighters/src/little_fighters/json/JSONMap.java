package little_fighters.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import little_fighters.content.materials.Material;
import little_fighters.content.materials.Tree;
import little_fighters.content.materials.danger.Danger;
import little_fighters.content.materials.danger.FireStatue;
import little_fighters.map.Map;

public class JSONMap {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		ArrayList<Material> dangers = new ArrayList<>();
		dangers.add(new FireStatue(10, 10));
		dangers.add(new FireStatue(10, 10));

		ArrayList<Material> materials = new ArrayList<>();
		materials.add(new Tree(0, 0));
		materials.add(new Tree(0, 0));
		materials.add(new Tree(0, 0));

		JSONMap j = new JSONMap(dangers, materials);
		try {
			j.read();
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	ArrayList<Material> materials = new ArrayList<>();
	ArrayList<Material> dangers = new ArrayList<>();

	public static String path = "";
	public static final String X = "X";
	public static final String Y = "Y";
	public static final String KIND = "KIND";
	public static final String MATERIALS = "MATERIALS";
	public static final String DANGERS = "DANGERS";

	public JSONMap() {
		try {
			path = new File(".").getCanonicalPath() + "/maps.txt";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONMap(ArrayList<Material> dangers, ArrayList<Material> materials) {

		try {
			path = new File(".").getCanonicalPath() + "/maps.txt";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.materials = materials;
		this.dangers = dangers;
	}

	public void write() throws IOException, JSONException {
		try {
			path = new File(".").getCanonicalPath() + "/maps.txt";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ----------- READ
		File rrr = new File(path);
		FileInputStream fis = new FileInputStream(rrr);
		byte[] data = new byte[(int) rrr.length()];
		fis.read(data);
		fis.close();

		String str = new String(data, "UTF-8");

		JSONArray jsonArray = new JSONArray(str);

		JSONObject map = new JSONObject();

		JSONArray mtrArray = new JSONArray();
		for (int counter = 0; counter < materials.size(); counter++) {
			JSONObject obj = new JSONObject();
			obj.put(X, materials.get(counter).getX());
			obj.put(Y, materials.get(counter).getY());
			String kind = materials.get(counter).getClass().toString().replaceAll("class ", "");
			obj.put(KIND, kind);
			mtrArray.put(obj);
		}

		JSONArray dngArray = new JSONArray();
		for (int counter = 0; counter < dangers.size(); counter++) {
			JSONObject obj = new JSONObject();
			obj.put(X, dangers.get(counter).getX());
			obj.put(Y, dangers.get(counter).getY());
			String kind = dangers.get(counter).getClass().toString().replaceAll("class ", "");
			obj.put(KIND, kind);
			dngArray.put(obj);
		}
		map.put(MATERIALS, mtrArray);
		map.put(DANGERS, dngArray);

		jsonArray.put(map);

		// ----------- WRITE
		FileWriter file = new FileWriter(path);
		file.write(jsonArray.toString());
		file.close();

	}

	public static ArrayList<Map> read()
			throws IOException, JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String path = "";
		try {
			path = new File(".").getCanonicalPath() + "/maps.txt";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Map> maps = new ArrayList<>();

		// ----------- READ
		File rrr = new File(path);
		FileInputStream fis = new FileInputStream(rrr);
		byte[] data = new byte[(int) rrr.length()];
		fis.read(data);
		fis.close();

		String str = new String(data, "UTF-8");

		JSONArray jsonArray = new JSONArray(str);

		// ------------- MAP LOOP
		for (int counter = 0; counter < jsonArray.length(); counter++) {

			ArrayList<Material> dangers = new ArrayList<>();
			ArrayList<Material> materials = new ArrayList<>();
			JSONObject obj = jsonArray.getJSONObject(counter);

			// ------------- MATERIL LOOP
			JSONArray mtrARRAY = obj.getJSONArray(MATERIALS);
			for (int counter2 = 0; counter2 < mtrARRAY.length(); counter2++) {
				String kind = mtrARRAY.getJSONObject(counter2).getString(KIND);
				int x = mtrARRAY.getJSONObject(counter2).getInt(X);
				int y = mtrARRAY.getJSONObject(counter2).getInt(Y);

				Material mtr = (Material) Class.forName(kind).getConstructor(int.class, int.class).newInstance(x, y);
				mtr.setX(x);
				mtr.setY(y);
				materials.add(mtr);
			}

			// ------------- DANGERS LOOP
			JSONArray dngARRAY = obj.getJSONArray(DANGERS);
			for (int counter2 = 0; counter2 < dngARRAY.length(); counter2++) {
				String kind = dngARRAY.getJSONObject(counter2).getString(KIND);
				int x = dngARRAY.getJSONObject(counter2).getInt(X);
				int y = dngARRAY.getJSONObject(counter2).getInt(Y);

				Material mtr = (Material) Class.forName(kind).getConstructor(int.class, int.class).newInstance(x, y);

				dangers.add(mtr);
			}

			maps.add(new Map(materials, dangers));

		}

		return maps;

	}

}
