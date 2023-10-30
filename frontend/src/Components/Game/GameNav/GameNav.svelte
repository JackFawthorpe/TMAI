<script lang="ts">
    import SocketConnectionDisplay from "./SocketConnectionDisplay.svelte";
    import PlayerBrowser from "./PlayerBrowser.svelte";
    import PlayerDeck from "./PlayerDeck.svelte";
    import type {Player} from "../../../Types/Player";
    import {playerStore, subscribeToStore} from "../../../apis/GameStore";

    export let connected: boolean;

    let currentTab: 'Players' | 'Cards' = 'Players';

    let currentPlayer: Player
    subscribeToStore(playerStore, (value) => {
        currentPlayer = value
    });

</script>

<div class="w-[40%] bg-white flex flex-col justify-between">
    {#if currentPlayer !== null}
        <div class="tab-nav">
            {#each ["Players", "Hand"] as tab, index}
                <div class="tab"
                     on:click={() => {currentTab = tab}}
                     class:tab-active={currentTab === tab}>
                <span class="tab-text">
                    {tab}
                </span>
                </div>
            {/each}
        </div>
    {/if}
    <div class="flex-grow">
        {#if currentTab === 'Players'}
            <PlayerBrowser/>
        {:else}
            <PlayerDeck/>
        {/if}
    </div>
    <div>
        <SocketConnectionDisplay connected={connected}/>
    </div>
</div>