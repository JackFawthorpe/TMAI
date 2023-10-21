<script lang="ts">
    import SocketConnectionDisplay from "../SocketConnectionDisplay.svelte";
    import PlayerBrowser from "../PlayerBrowser.svelte";

    let currentTab: 'Players' | 'Cards' = 'Players';

    export let connected: boolean;

</script>

<div class="w-[40%] bg-white flex flex-col justify-between">
    <div class="tab-nav">
        {#each ["Players", "Cards"] as tab, index}
            <div class="tab"
                 on:click={() => {currentTab = tab}}
                 class:tab-active={currentTab === tab}>
                <span class="tab-text">
                    {tab}
                </span>
            </div>
        {/each}
    </div>
    <div class="flex-grow">
        {#if currentTab === 'Players'}
            <PlayerBrowser/>
        {:else if currentTab === 'cards'}
            <h3>Test</h3>
        {:else}
            <h3>Blessed</h3>
        {/if}
    </div>
    <div>
        <SocketConnectionDisplay connected={connected}/>
    </div>
</div>