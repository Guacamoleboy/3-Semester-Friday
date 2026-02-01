// showMark: false to remove dots
// sx styling directly in .tsx file

import Box from '@mui/material/Box';
import { LineChart } from '@mui/x-charts/LineChart';

const uData = [6, 6, 5, 5, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2];
const pData = [6, 5, 5, 4, 4, 3, 3, 3, 2, 2, 2, 1, 1, 1];
const xLabels = [
  'Start','2','3','4','5','6','7','8','9','10','11','12','13','Slut'
];

export default function SimpleLineChart() {
  return (
    <Box sx={{ width: '100%', height: 300, pointerEvents: 'none' }}>
      <LineChart
        className="moodmap-graph-stock"
        series={[
          { data: pData},
          { data: uData},
          
        ]}
        xAxis={[{ scaleType: 'point', data: xLabels, height: 30 }]}
        yAxis={[{ width: 20 }]}
        margin={{ top: 30, right: 30 }}
        sx={{
          // Y-axis labels
          "& .MuiChartsAxis-left .MuiChartsAxis-tickLabel": {
            fill: "white",
            strokeWidth: 0.4,
          },
          // X-axis labels
          "& .MuiChartsAxis-bottom .MuiChartsAxis-tickLabel": {
            fill: "white",
            strokeWidth: 0.4,
          },
          // Y-axis line
          "& .MuiChartsAxis-left .MuiChartsAxis-line": {
            stroke: "white",
            strokeWidth: 0.75,
          },
          // X-axis line
          "& .MuiChartsAxis-bottom .MuiChartsAxis-line": {
            stroke: "white",
            strokeWidth: 0.75,
          },
        }}
      />
    </Box>
  );
}
