<script lang="ts">
    import type {Card} from "../../Types/Card";
    import {TagImage} from "../../Types/Card";
    import {SvelteComponent} from "svelte";

    export let card: Card;
    export let footerSlot: SvelteComponent;
</script>

<div class="playCard">
    <div class="flex-grow">
        <div class="flex justify-between">
            <h5 class="text-2xl font-bold tracking-tight text-black flex-shrink">{card.title}</h5>

            <!-- Credit Cost -->
            <div class="relative flex-none h-12 w-12">
                <img alt="credit" src="src/Resources/img/Credit.png"/>
                <h1 class="absolute text-2xl text-black font-bold top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">{card.cost}</h1>
            </div>
        </div>
        <div class="flex space-x-1 h-12 w-12">
            {#each card.tags as tag}
                <img src={TagImage[tag]} alt="tag">
            {/each}
        </div>
    </div>
    <slot {footerSlot}/>
</div>