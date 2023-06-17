<script lang="ts">
    import {API} from "../apis/API";
    import {navigate} from "svelte-routing";

    let playerCount: number = 2;
    let playerNames: string[] = ["", ""];
    let hasError: boolean = false;

    $: {
        hasError = playerCount < 0 || playerCount > 6

        let updated = [];
        for (let i = 0; i < playerCount; i++) {
            updated[i] = playerNames[i] === undefined ? "" : playerNames[i]
        }
        playerNames = updated;
    }
    async function handleSubmit() {
        if (hasError) {
            return;
        }

        try {
            await API.postGame(playerNames);
            navigate("/game");
        } catch (e) {
            console.log(e.message);
        }
    }
</script>

<div class="container-fluid flex justify-center pt-5">
    <div class="w-full max-w-xs">
        <form class="bg-white drop-shadow-2xl rounded px-8 pt-6 pb-8 mb-4">
            <div class="mb-4">
                <label class="block text-gray-700 text-lg font-bold mb-2" for="playerCount">
                    Player Count
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 text-lg leading-tight focus:outline-none focus:shadow-outline" id="playerCount" type="number" bind:value={playerCount} min="1" max="6">
                {#if hasError}
                    <span class="text-sm text-red-600"> Invalid player count, please select a number between 1 and 6</span>
                {/if}
            </div>
            {#each playerNames as playerName, id}
                <div class="mb-4">
                    <label class="block text-gray-700 text-lg font-bold mb-2" for="playerName {id}">
                        Player {id + 1}:
                    </label>
                    <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 text-lg leading-tight focus:outline-none focus:shadow-outline" id="playerName {id}" bind:value={playerName}>
                </div>
            {/each}
            <div class="flex justify-center">
                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" on:click={handleSubmit} type="button">
                    Start Game
                </button>
            </div>
        </form>
    </div>
</div>