// Fake Data till API is added

import type { DataPoint } from "../../types/ui/GraphStockType";

export function generateFakeGraphData(days: number): DataPoint[] {
    const maxPoints = 14;
    const data: DataPoint[] = [];
    const now = Date.now();

    const step = Math.ceil(days / maxPoints); 

    for (let i = 0; i < days; i += step) {
        const x = now + i * 86400000; 
        const y = Math.floor(Math.random() * 6) + 1; 
        data.push({ x, y });
        if (data.length >= maxPoints) break; 
    }

    return data;
}