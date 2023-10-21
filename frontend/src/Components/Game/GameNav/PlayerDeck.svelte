<script lang="ts">
    import type {Player} from "../../../Types/Player";
    import {playerStore, subscribeToStore} from "../../../apis/Contexts";
    import CardCard from "../../Card/CardCard.svelte";
    import type {Card} from "../../../Types/Card";

    let player: Player = null;
    subscribeToStore(playerStore, (value) => {
        console.log(player)
        player = value
    });
    let hand: Card[];
    $: hand = player?.playerDeck.hand ?? [];

</script>

<div class="container-fluid h-full grid grid-cols-2 p-2 gap-2 overflow-y-scroll">
    {#each hand as card}
        <CardCard card={card}/>
    {/each}
</div>