<script lang="ts">

    import type {Player} from "../../Types/Player";
    import {API} from "../../apis/API";

    export let player: Player;
    export let currentPlayerContext: {playerId: number, setPlayerId: (id: number) => {}}

    const handleTakePlayer = async () => {
        isRequesting = true;
        if (await API.takePlayer(player.id)) {
            currentPlayerContext.setPlayerId(player.id);
        }
        isRequesting = false;
    }


    const resourceImageArray = [
        "src/Resources/img/Credit.png",
        "src/Resources/img/Steel.png",
        "src/Resources/img/Titanium.png",
        "src/Resources/img/Plant.png",
        "src/Resources/img/Energy.png",
        "src/Resources/img/Heat.png"
    ];

    const playerHandlerImageArray = [
        "src/Resources/img/ComputerPlayer.png",
        "src/Resources/img/HumanPlayer.png"

    ]

    let isRequesting = false;
</script>

<div class="border border-gray relative">

    <!-- Handler Icon -->
    <div class=" p-3 rounded-full absolute top-[15px] left-[15px]"
        class:bg-green-400={currentPlayerContext.playerId === player.id}
        class:bg-red-400={currentPlayerContext.playerId !== player.id}>
        <img src={playerHandlerImageArray[player.human ? 1 : 0]} class="h-6 w-6"/>
    </div>

    <!-- Title -->
    <div class="flex justify-center">
        <h3 class="pt-3 text-center text-xl text-indigo-800 font-bold">{player.name}</h3>
    </div>

    <!-- Take user -->
    {#if !player.human && !isRequesting && currentPlayerContext.playerId === -1}
        <button class="btn btn-primary absolute top-[15px] right-[15px]" on:click={handleTakePlayer}>
            <img src="src/Resources/img/DoubleArrowRight.png" class="h-6 w-6"/>
        </button>
    {/if}

    <!-- Resources -->
    <h3 class="pt-3 text-center text-l text-black font-bold pb-2">Resources</h3>
    <div class="grid grid-cols-6 justify-items-center">
        {#each player.inventory.count as count, i}
            <div class="group relative h-12 w-12">
                <img src={resourceImageArray[i]}/>
                <div class="group-hover:-z-10 h-8 w-8 absolute bg-white rounded-full top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
                    <h1 class="text-2xl text-center text-black font-bold ">{count}</h1>
                </div>
            </div>
        {/each}
    </div>

    <!-- Production -->
    <h3 class="pt-3 text-center text-l text-black font-bold pb-2">Production</h3>
    <div class="grid grid-cols-6 justify-items-center pb-3">
        {#each player.inventory.count as count, i}
            <div class="group relative h-12 w-12">
                <img src={resourceImageArray[i]}/>
                <div class="group-hover:-z-10 h-8 w-8 absolute bg-white rounded-full top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
                    <h1 class="text-2xl text-center text-black font-bold ">{count}</h1>
                </div>
            </div>
        {/each}
    </div>
</div>
