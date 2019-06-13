# Minecraft Video Generator by HYWT


>MY CODE IS BAD, I KNOW. IT WORKS. MAKE IT BETTER FOR ME IF YOU WANT!

---

### Requirements:

* `ffmpeg` , [download link here](https://ffmpeg.org/)

* Lots of memory for Minecraft (At least 4 GB)



### How to generate a video:

* 1.You'll need to install `ffmpeg`

* 2.Run computer command 

  `ffmpeg -i <Your input file> -vf scale=<width>:<height> -r 20 ./output/img%05d.png -vn ./output/audio.ogg`

* Remember, higher resolution or more frames = more memory usage (e.g. 320x180 with 3000 frames = 2750 MB Memory)

* 3.Open the application

* 4.Select input folder, output folder

* 5.Configure output settings

* 6.Click "Generate Pack"

* 7.Copy to Minecraft folder

* 8.Open Minecraft, load the pack and enter the world

* 9.Give yourself a repeating command block 

  `/give @s repeating_command_block`

* 10.Enter this command into the command block, set at running

  `/execute @p ~ ~ ~ function <Your package ID>/tick`

* 11.Run chat command 

 `/function <Your package ID>/init`

* 12.Enjoy!
