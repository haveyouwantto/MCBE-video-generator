### Minecraft Video Generator by HYWT

#### **Requirements:**
1. Install [**ffmpeg**](https://ffmpeg.org/).
2. Allocate at least **4 GB of memory** for Minecraft.

#### **How to Generate a Video:**

1. **Install ffmpeg** (if not already installed).
   
2. **Extract Images and Audio** from your video:
   Run this command in your terminal:
   ```bash
   ffmpeg -i <input-file> -vf scale=<width>:<height> -r 20 ./output/img%05d.png -vn ./output/audio.ogg
   ```
   - **`<input-file>`**: Your video file.
   - **`<width>:<height>`**: Resolution (e.g., 320x180).
   - **`-r 20`**: Frame rate (20 FPS recommended).
   - **Output**: Images (`img%05d.png`) and audio (`audio.ogg`) will be saved to the `output/` folder.

   **Tip**: Higher resolution or more frames require more memory (e.g., 320x180 with 3000 frames = 2750 MB of memory).

3. **Open the Video Generator application.**

4. **Select input and output folders.**
   - **Input folder**: Where your extracted images and audio are stored.
   - **Output folder**: Where the generated Minecraft pack will be saved.

5. **Configure output settings** as per your video and Minecraft setup.

6. **Generate the Pack** by clicking the "Generate Pack" button.

7. **Copy the pack** to your Minecraft resource pack folder:
   - **Windows**: `%appdata%/.minecraft/resourcepacks`
   - **Mac**: `~/Library/Application Support/minecraft/resourcepacks`
   - **Linux**: `~/.minecraft/resourcepacks`

8. **Load Minecraft**, go to settings, and activate the pack.

9. **Get a repeating command block** in-game by typing:
   ```bash
   /give @s repeating_command_block
   ```

10. **Set up the command block** with this command:
   ```bash
   /execute @p ~ ~ ~ function <Your-package-ID>/tick
   ```
   - Replace `<Your-package-ID>` with the name of your generated pack.

11. In chat, run the initialization command:
   ```bash
   /function <Your-package-ID>/init
   ```

12. **Enjoy** your video within Minecraft!
