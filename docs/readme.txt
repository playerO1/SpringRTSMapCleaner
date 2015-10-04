
(C) PlayerO1 2015. GNU GPL V2 license.
This utility (software) can help to find and clear old maps in Spring RTS game. 
Find maps and calculate replays per maps, show window with map list.

You can using alternative way for clear maps:
1. Just clear all map folder (and demo folder, if you don't want collect replays). But you will be need to redownload maps.
2. use command prompt / linux shell command (as ls, grep, sort, etc.), but you can delete same good map without preview.

FAQ:
1. How to launch this utility?
- You need to have Java machine (JRE). Open CMD (or Start->Run command) and type "java -jar [path to OldMapCleaner.jar]". If you have Linux, use linux shell.
2. How fast do remove? I wan't to learn this utility?
- press button "Show map list", after opening new window see the list of remove maps (mark as red). If you want to delete this, switch to last window ("Map cleaner for Spring RTS") and press button "Remove maps", then press "Yes" after that press "Ok". That all.
3. I'm not see maps in the "Maps list", what to do?
- Check correct path in the first window to "Spring RTS map folders".
4. All maps automatic mark to delete and show that no one plays on all maps.
- Check correct path in the first window to "Spring replays folders".
5. I don't want mark to delete map automaticly.
- before first press button "Show map list" unckeck "auto mark old".
3. Whay files to remove more that map files?
- To remove files add Spring Lobby cashe files too. Lobby+Map.
4. I try remove maps from C:\Program files\Spring\... folder, but removed 0 files and I see messages "Can not remove> C:\...".
- If you have read-only access to folder (access from not computer administrator account), you can not easy remove files from same folder.


What to do in the next versions:
1) Auto detect OS depended and Sring Engine version paths
2) Read from ZIP and 7z archives demos
3) decrease GUI lags, where show too much maps in list (ower 500)
3) Drag & Drop folder into window, may be Drag&Drop for windows shortcut
4) Button "Clear old demo files [before date]"
5) Generate script (SH for Linux, Batch for Windows) for remove maps
6) Support more Lobby's cashe (or NOTA lobby, WEB Lobby, Zero-K lobby; or more map details about map (size, comment, etc.)
7) Read info from map file (.sd7) without lobby cashe
