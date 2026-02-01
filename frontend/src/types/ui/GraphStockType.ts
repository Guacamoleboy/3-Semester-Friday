export type DataPoint = {
    x: number
    y: number
}

export interface LineGraphProps {
    data: DataPoint[]
    width?: number
    height?: number
    padding?: number
}