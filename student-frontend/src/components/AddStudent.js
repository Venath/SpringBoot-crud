import React, { useState } from 'react';
import { addStudent } from '../services/studentService';

const AddStudent = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!name || !email) {
            setError('Name and email are required.');
            return;
        }

        const student = { name, email };

        try {
            await addStudent(student);
            setName('');
            setEmail('');
            setError('');
            alert('Student added successfully');
        } catch (error) {
            setError('Failed to add student.');
        }
    };

    return (
        <div>
            <h2>Add New Student</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name:</label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <button type="submit">Add Student</button>
            </form>
        </div>
    );
};

export default AddStudent;
