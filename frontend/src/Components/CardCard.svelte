<script lang="ts">
    export let card: Card;
</script>

<div class="p-6 bg-white border border-gray-200 rounded-lg shadow-xl min-h-[150px]">
    <div class="flex justify-between">
        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 flex-shrink">{card.title}</h5>
        <div class="relative flex-none float-right h-12 w-12">
            <img src="src/Resources/Credit.png" class="object-cover h-12 w-12" alt="credit"/>
            <h1 class="absolute text-2xl text-black font-bold top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">{card.cost}</h1>
        </div>
    </div>
</div>