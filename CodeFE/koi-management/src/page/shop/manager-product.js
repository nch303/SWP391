import React, { useState, useEffect } from 'react';

/*************  ✨ Codeium Command 🌟  *************/
const ManagerProduct = () => {
    const [products, setProducts] = useState([]);
    const [productId, setProductId] = useState(null);
    const product = products.find(p => p.id === productId);

    useEffect(() => {
        const sampleData = [
            { id: 1, name: 'Koi Food', price: 10.99, description: 'High quality koi food for optimal growth and health', image: 'koi-food.png', category: 'Food' },
            { id: 2, name: 'Pond Salt', price: 24.99, description: 'Natural salt for maintaining healthy water parameters', image: 'pond-salt.jpg', category: 'Water Care' },
            { id: 3, name: 'Water Test Kit', price: 34.98, description: 'Monitor your pond water parameters with this complete kit', image: 'water-test-kit.jpg', category: 'Water Care' },
            { id: 4, name: 'Koi Net', price: 7.99, description: 'Good quality net for catching koi fish', image: 'koi-net.jpg', category: 'Health' },
            { id: 5, name: 'Pond Pump', price: 99.99, description: 'Powerful pump for circulating water in your pond', image: 'pond-pump.jpg', category: 'Decorations' },
        ];
        setProducts(sampleData);
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
        const idx = products.findIndex(p => p.id === productId);
        if (idx >= 0) {
            const newProduct = { ...products[idx], ...product };
            const newProducts = [...products];
            newProducts[idx] = newProduct;
            setProducts(newProducts);
        } else {
            const newProduct = { ...product, id: products.length + 1 };
            setProducts([...products, newProduct]);
        }
    };

    const handleDelete = (id) => {
        setProducts(products.filter(p => p.id !== id));
    };

    return (
        <div className="manager-product">
            <h2 className='title mt-5 text-center'>Manager Products</h2>

            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Category</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map(product => (
                        <tr key={product.id} className="product-row">
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.description}</td>
                            <td><img src={product.image} alt={product.name} width="100" /></td>
                            <td>{product.category}</td>
                            <td>
                                <button onClick={() => setProductId(product.id)}>Edit</button>
                                <button onClick={() => handleDelete(product.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ManagerProduct;

const styles = {
    managerProduct: {
        maxWidth: '1000px',
        margin: '0 auto',
        padding: '20px'
    },
    productRow: {
        borderBottom: '1px solid lightgray',
        padding: '10px'
    }
};

