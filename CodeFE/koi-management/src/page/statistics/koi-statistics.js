import React from 'react';

import { Line } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    LineElement,
    CategoryScale,
    LinearScale,
    PointElement,
} from 'chart.js';

ChartJS.register(
    LineElement,
    CategoryScale,
    LinearScale,
    PointElement,
);

function KoiStatistics() {

    const dataWeightVsAge = {
        labels: ['0 year', '1 year', '2 years', '3 years', '4 years', '5 years'],
        datasets: [{
            label: 'Weight in kg',
            data: [0.2, 0.5, 0.8, 1.2, 1.5, 2.0],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
            ],
            borderWidth: 5
        }]
    }

    const dataLengthVsAge = {
        labels: ['0 year', '1 year', '2 years', '3 years', '4 years', '5 years'],
        datasets: [{
            label: 'Length in cm',
            data: [10, 15, 20, 25, 30, 35],
            backgroundColor: [
                'rgba(54, 162, 235, 0.2)',
            ],
            borderColor: [
                'rgba(54, 162, 235, 1)',
            ],
            borderWidth: 5
        }]
    }

    return (
        <div>
            <h1>Koi Statistics</h1>
            <div style={{ width: '40%', margin: 'auto' }}>
                <Line data={dataWeightVsAge} height={50} width={100} />
                <Line data={dataLengthVsAge} height={50} width={100} />
            </div>
        </div>
    );
}

export default KoiStatistics;

