<script lang="ts">
    import type {Card} from "../Types/Card";
    import {TagImage} from "../Types/Card";

    export let card: Card;
</script>

<div class="p-6 bg-white border border-gray-200 rounded-lg shadow-xl min-h-[150px]">
    <div class="flex justify-between">
        <h5 class="text-2xl font-bold tracking-tight text-black flex-shrink">{card.title}</h5>

        <!-- Credit Cost -->
        <div class="relative flex-none h-12 w-12">
            <img src="src/Resources/Credit.png" alt="credit"/>
            <h1 class="absolute text-2xl text-black font-bold top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">{card.cost}</h1>
        </div>
    </div>
    <div class="flex space-x-1 h-12 w-12">
        {#each card.tags as tag}
            <img src={TagImage[tag]} alt="tag">
        {/each}
    </div>
</div>