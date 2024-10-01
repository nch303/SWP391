import React, { useState, useEffect } from 'react';

const ManagerProduct = () => {
    const [products, setProducts] = useState([]);
    const [name, setName] = useState('');
    const [price, setPrice] = useState(0);
    const [description, setDescription] = useState('');
    const [image, setImage] = useState('');
    const [category, setCategory] = useState('');
    const [productId, setProductId] = useState(null);

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

    useEffect(() => {
        const id = productId;
        if (id) {
            const product = products.find(product => product.id === parseInt(id));
            setName(product.name);
            setPrice(product.price);
            setDescription(product.description);
            setImage(product.image);
            setCategory(product.category);
        }
    }, [productId, products]);

    const handleSubmit = (e) => {
        e.preventDefault();
        const product = {
            name,
            price,
            description,
            image,
            category,
        };
        if (productId) {
            const index = products.findIndex(product => product.id === parseInt(productId));
            const newProducts = [...products];
            newProducts[index] = product;
            setProducts(newProducts);
        } else {
            const newProduct = { ...product, id: products.length + 1 };
            setProducts([...products, newProduct]);
        }
    };

    const handleDelete = (id) => {
        setProducts(products.filter(product => product.id !== id));
    };

    return (
        <div className="manager-product">
            <h2 className='title mt-5 text-center'>Manager Products</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Name:
                    <input type="text" value={name} onChange={e => setName(e.target.value)} />
                </label>
                <label>
                    Price:
                    <input type="number" value={price} onChange={e => setPrice(e.target.value)} />
                </label>
                <label>
                    Description:
                    <textarea value={description} onChange={e => setDescription(e.target.value)} />
                </label>
                <label>
                    Image:
                    <input type="text" value={image} onChange={e => setImage(e.target.value)} />
                </label>
                <label>
                    Category:
                    <select value={category} onChange={e => setCategory(e.target.value)}>
                        <option value="">Select a category</option>
                        <option value="food">Food</option>
                        <option value="accessories">Accessories</option>
                    </select>
                </label>
                <button type="submit">Submit</button>
                {productId && <button onClick={() => handleDelete(productId)}>Delete</button>}
            </form>
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

