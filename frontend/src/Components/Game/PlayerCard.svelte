<script lang="ts">

    import type {Player} from "../../Types/Player";

    export let player: Player;
    const imageArray = [
        "src/Resources/Credit.png",
        "src/Resources/Steel.png",
        "src/Resources/Titanium.png",
        "src/Resources/Plant.png",
        "src/Resources/Energy.png",
        "src/Resources/Heat.png"
    ];
</script>

<div class="border border-gray">
    <h3 class="pt-3 text-center text-xl text-indigo-800 font-bold">{player.name}</h3>
    <h3 class="pt-3 text-center text-l text-black font-bold pb-2">Resources</h3>
    <div class="grid grid-cols-6 justify-items-center">
        {#each player.inventory.count as count, i}
            <div class="group relative h-12 w-12">
                <img src={imageArray[i]}/>
                <div class="group-hover:-z-10 h-8 w-8 absolute bg-white rounded-full top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
                    <h1 class="text-2xl text-center text-black font-bold ">{count}</h1>
                </div>
            </div>
        {/each}
    </div>
    <h3 class="pt-3 text-center text-l text-black font-bold pb-2">Production</h3>
    <div class="grid grid-cols-6 justify-items-center pb-3">
        {#each player.inventory.count as count, i}
            <div class="group relative h-12 w-12">
                <img src={imageArray[i]}/>
                <div class="group-hover:-z-10 h-8 w-8 absolute bg-white rounded-full top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
                    <h1 class="text-2xl text-center text-black font-bold ">{count}</h1>
                </div>
            </div>
        {/each}
    </div>
</div>
