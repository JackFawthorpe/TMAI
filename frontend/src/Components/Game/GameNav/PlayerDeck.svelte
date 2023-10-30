<script lang="ts">
    import type {Player} from "../../../Types/Player";
    import {playerStore, subscribeToStore} from "../../../apis/GameStore";
    import CardCard from "../../Card/CardCard.svelte";
    import type {Card} from "../../../Types/Card";

    let player: Player = null;
    subscribeToStore(playerStore, (value) => {
        player = value
    });
    let hand: Card[];
    $: hand = player?.playerDeck.hand ?? [];

</script>

<div class="container-fluid h-full grid grid-cols-2 p-2 gap-2 overflow-y-scroll">
    {#each hand as card}
        <CardCard card={card}>
            <button class="btn btn-primary">Play Card</button>
        </CardCard>
    {/each}
</div>