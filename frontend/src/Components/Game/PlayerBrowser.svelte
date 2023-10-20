<script lang="ts">

    import type {Game} from "../../Types/Game";
    import PlayerCard from "./PlayerCard.svelte";
    import type {Player} from "../../Types/Player";

    export let players: Player[];

    let currentPlayerContext = {
        playerId: -1,
        setPlayerId: (id: number) => {
            currentPlayerContext.playerId = id;
        }
    }

</script>

<div class="grid grid-cols-1 xl:grid-cols-2">
    {#each players as player}
        <PlayerCard player={player} currentPlayerContext={currentPlayerContext}/>
    {/each}
</div>